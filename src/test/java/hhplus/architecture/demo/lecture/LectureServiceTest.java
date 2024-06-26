package hhplus.architecture.demo.lecture;

import hhplus.architecture.demo.domain.Enroll;
import hhplus.architecture.demo.domain.Lecture;
import hhplus.architecture.demo.repository.EnrollJpaRepository;
import hhplus.architecture.demo.repository.LectureJpaRepository;
import hhplus.architecture.demo.service.EnrollService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class LectureServiceTest {

    private Enroll enroll;
    private EnrollService enrollService;
    @Mock
    private EnrollJpaRepository enrollRepository;

    @Mock
    private LectureJpaRepository lectureRepository;

    @Test
    @DisplayName("유저가 신청한 특강이 존재하는지 조회")
    void testUserIdExist()
    {
        //given
        Long userId = 100L;
        Long lectureId = 200L;
        Enroll enroll = new Enroll(userId, lectureId);

        //when
        when(enrollRepository.findAllByUserId(userId)).thenReturn((List<Enroll>) enroll);
        List<Enroll> result = enrollService.getApplication(userId);

        //then
        assertEquals(enroll, result);
    }

    @Test
    @DisplayName("특강 신청 최대 인워수 초과한 경우 신청 실패 테스트")
    void testUserEnrollFail()
    {
        //given
        Lecture lecture = new Lecture("특강103", 20, 20, LocalDateTime.now());
        Long lectureId = lecture.getLectureId();
        Long userId = 100L;

        Enroll enroll = new Enroll(userId, lectureId);
        //when
        when(enrollRepository.save(enroll)).thenReturn(enroll);
        boolean result = enrollService.isEnrollExist(userId, lectureId);

        //then
        assertEquals(enroll, result);
    }
}
