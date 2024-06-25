package hhplus.architecture.demo.lecture;

import hhplus.architecture.demo.domain.Lecture;
import hhplus.architecture.demo.repository.EnrollRepository;
import hhplus.architecture.demo.repository.LectureRepository;
import hhplus.architecture.demo.service.EnrollService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

public class LectureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EnrollService enrollService;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private EnrollRepository enrollRepository;

    @Test
    @DisplayName("특정 특강 조회")
    void testGetLectureById() throws Exception
    {
        // given
        String title = "특강101";
        Integer curCapacity = 0;
        Integer maxCapacity = 30;
        LocalDateTime openAt = LocalDateTime.of(2024, 6, 30, 13, 0, 0);

        Lecture lecture = new Lecture(title, curCapacity, maxCapacity, openAt);
        Long lectureId = lecture.getLectureId();

        // when
        when(lectureRepository.findByLectureId(lectureId).get(0)).thenReturn(lecture);
        // then
        mockMvc.perform(get("/")
                        .param("lectureId", String.valueOf(lectureId)))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
