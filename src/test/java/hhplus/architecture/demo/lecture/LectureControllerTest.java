package hhplus.architecture.demo.lecture;

import hhplus.architecture.demo.domain.Enroll;
import hhplus.architecture.demo.domain.Lecture;
import hhplus.architecture.demo.repository.EnrollJpaRepository;
import hhplus.architecture.demo.repository.LectureJpaRepository;
import hhplus.architecture.demo.service.EnrollService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class LectureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EnrollService enrollService;

    @Test
    @DisplayName("특강 조회 설공 케이스")
    void doesLectureExistSuccess() throws Exception
    {
        //given
        Lecture lecture = new Lecture("특강101", 29, 30, LocalDateTime.now());
        Lecture mocklecture = enrollService.create(lecture);

        mockMvc.perform(get("/lectures")
                        .param("lectureId", String.valueOf(mocklecture.getLectureId())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(mocklecture.getTitle()));
    }



}
