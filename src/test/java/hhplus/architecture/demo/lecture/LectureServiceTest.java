package hhplus.architecture.demo.lecture;

import hhplus.architecture.demo.domain.Enroll;
import hhplus.architecture.demo.domain.Lecture;
import hhplus.architecture.demo.repository.EnrollRepository;
import hhplus.architecture.demo.repository.LectureRepository;
import hhplus.architecture.demo.service.EnrollService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class LectureServiceTest {

    private Enroll enroll;

    private EnrollService enrollService;
    @Mock
    private EnrollRepository enrollRepository;

    @Mock
    private LectureRepository lectureRepository;

    @Test
    @DisplayName("유저가 신청한 특강이 존재하는지 조회")
    void testUserIdExist()
    {
        //given
        Long userId = 100L;
        Long lectureId = 200L;
        Enroll enroll = new Enroll(userId, lectureId);

        //when
        when(enrollRepository.findByUserId(userId)).thenReturn((List<Enroll>) enroll);
        List<Enroll> result = enrollService.getApplication(userId);

        //then
        assertEquals(enroll, result);
    }
}
