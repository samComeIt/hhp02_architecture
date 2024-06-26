package hhplus.architecture.demo.lecture;

import hhplus.architecture.demo.domain.Lecture;
import hhplus.architecture.demo.service.EnrollRepository;
import hhplus.architecture.demo.service.EnrollService;
import hhplus.architecture.demo.service.LectureRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("특정한 특강에 최대인원수N를 초과 할 경우번째 학생이 신청 할 경우, 신청 실패 테스트")
    void testAddEnroll() throws Exception {
        // given
        Lecture lecture = new Lecture("특강102", 19, 20, LocalDateTime.now());
        Long lectureId = lecture.getLectureId();
        Long userId = 100L;

        // when, then
        mockMvc.perform(post("/lectures/apply")
                        .param("lectureId", String.valueOf(lectureId))
                        .param("userId", String.valueOf(userId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
