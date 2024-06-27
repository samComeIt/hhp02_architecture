package hhplus.architecture.demo.lecture;

import hhplus.architecture.demo.domain.Enroll;
import hhplus.architecture.demo.domain.Lecture;
import hhplus.architecture.demo.repository.EnrollJpaRepository;
import hhplus.architecture.demo.repository.LectureJpaRepository;
import hhplus.architecture.demo.service.EnrollRepository;
import hhplus.architecture.demo.service.EnrollService;
import hhplus.architecture.demo.service.LectureRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private EnrollService enrollService;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private EnrollRepository enrollRepository;

    @Test
    @Transactional
    @DisplayName("특정한 특강에 최대인원수N를 초과 할 경우번째 학생이 신청 할 경우, 신청 실패 테스트")
    void testAddEnroll() throws Exception {
        //given
        Lecture lecture = new Lecture("특강101", 29, 30, LocalDateTime.now());
        Lecture mocklecture = enrollService.create(lecture);
        Long lectureId = mocklecture.getLectureId();

        //when
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    enrollService.apply(1L, lectureId);
                }),
                CompletableFuture.runAsync(() -> {
                    enrollService.apply(2L, lectureId);
                }),
                CompletableFuture.runAsync(() -> {
                    enrollService.apply(3L, lectureId);
                })
        ).join();

        //then
        List<Enroll> enroll = enrollRepository.findAllByUserId(1L);
        assertNotNull(enroll);
    }
}
