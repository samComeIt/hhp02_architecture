package hhplus.architecture.demo.lecture;

import hhplus.architecture.demo.domain.Lecture;
import hhplus.architecture.demo.repository.EnrollJpaRepository;
import hhplus.architecture.demo.repository.LectureJpaRepository;
import hhplus.architecture.demo.service.EnrollService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

public class LectureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EnrollService enrollService;

    @Autowired
    private LectureJpaRepository lectureRepository;

    @Autowired
    private EnrollJpaRepository enrollRepository;

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
        when(lectureRepository.findAllByLectureId(lectureId).get(0)).thenReturn(lecture);
        // then
        mockMvc.perform(get("/")
                        .param("lectureId", String.valueOf(lectureId)))
                .andDo(print())
                .andExpect(status().isOk());
    }



}
