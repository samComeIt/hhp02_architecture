package hhplus.architecture.demo.lecture;

import hhplus.architecture.demo.domain.Enroll;
import hhplus.architecture.demo.domain.Lecture;
import hhplus.architecture.demo.repository.EnrollJpaRepository;
import hhplus.architecture.demo.repository.LectureJpaRepository;
import hhplus.architecture.demo.service.EnrollService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LectureServiceTest {
    @Mock
    private EnrollJpaRepository enrollRepository;

    @Mock
    private LectureJpaRepository lectureRepository;

    @InjectMocks
    private EnrollService enrollService;

    @Test
    @DisplayName("유저가 신청한 특강이 존재하는지 조회")
    @Transactional
    void testUserIdExist()
    {
        //given
        Lecture lecture = new Lecture("특강101", 29, 30, LocalDateTime.now());
        Long userId = 100L;
        Long lectureId = lecture.getLectureId();
        Enroll mockEnroll = new Enroll(userId, lecture);

        //when
        when(enrollRepository.findByEnrollId(mockEnroll.getEnrollId())).thenReturn(mockEnroll);
        Enroll createEnroll = enrollRepository.save(mockEnroll);

        //then
        assertNotNull(createEnroll);
        assertEquals(lecture.getCurCapacity(), 29);
        assertEquals(mockEnroll.getUserId(), createEnroll.getUserId());
    }

    @Test
    @DisplayName("유저가 신청한 특강이 존재하는지 조회 성공 테스트")
    void testUserEnrollSuccess()
    {
        //given
        Lecture lecture = new Lecture("특강103", 20, 20, LocalDateTime.now());

        Long userId = 100L;
        Enroll mockEnroll = new Enroll(userId, lecture);
        //when
        when(enrollRepository.save(mockEnroll)).thenReturn(mockEnroll);
        List<Enroll> enrolls = enrollRepository.findAllByUserId(userId);

        boolean result = enrolls.stream().filter(enroll -> enroll.getUserId() == userId).toList().size() != 0;

        //then
        assertEquals(false, result);
    }

    @Test
    @DisplayName("특강 신청 최대 인워수 초과한 경우 신청 실패 테스트")
    void testEnrollOverCapacityFail()
    {
        //given
        Lecture mockLecture = new Lecture("특강103", 20, 20, LocalDateTime.now());

        //when
        when(lectureRepository.save(mockLecture)).thenReturn(mockLecture);
        mockLecture.increaseEnrollCount();

        assertEquals(mockLecture.getCurCapacity(), 20);

    }
}
