package hhplus.architecture.demo.controller;

import hhplus.architecture.demo.controller.dto.RequestDTO;
import hhplus.architecture.demo.controller.dto.ResponseDTO;
import hhplus.architecture.demo.domain.Enroll;
import hhplus.architecture.demo.domain.Lecture;
import hhplus.architecture.demo.service.EnrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class EnrollController {

    // STEOP03
    // ERD 작성
    // 1. 특강 신청 API POST /lectures/apply (30명 까지만)
    // 2. 특강 선택 API GET /lectures
    // 3. 특강 신청 완료 여부 조회 API GET /lectures/application/{userId} (true or false)

    // STEP04
    // 강의와 각 강의별 날짜가 추가 가능한 구조의 DB가 구현되었는지
    // (동시성) 각 강의 별로 최대 30명까지만 정상적으로 요청되도록 기능 구현

    private final EnrollService enrollService;

    /**
     * 특강 신청
     */
    @PostMapping("/apply")
    public Enroll apply(@RequestBody Enroll enroll)
    {
        return enrollService.apply(enroll);
    }

    /**
     * 특강 선택
     */
    @GetMapping("/")
    public Lecture getLecture(@RequestParam long lectureId)
    {
        return enrollService.getLecture(lectureId);
    }

    /**
     * 특강 신청 완료 여부 리스트 조회
     */
    @GetMapping("/application/{userId}")
    public boolean application(@PathVariable long userId, @RequestParam long lectureId)
    {
        return enrollService.isEnrollExist(userId, lectureId);
    }
}
