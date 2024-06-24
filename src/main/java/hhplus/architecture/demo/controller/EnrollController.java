package hhplus.architecture.demo.controller;

import hhplus.architecture.demo.controller.dto.RequestDTO;
import hhplus.architecture.demo.controller.dto.ResponseDTO;
import hhplus.architecture.demo.domain.Enroll;
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

    private final EnrollService enrollService;


    @PostMapping("/apply")
    public Enroll apply(@RequestBody Enroll enroll)
    {
        return enrollService.apply(enroll);
    }

    /**
     * 특강 신청 완료 여부 리스트 조회
     * @param userId
     * @return
     */
    @GetMapping("/application/{userId}")
    public List<Enroll> application(@PathVariable long userId)
    {
        return enrollService.getApplication(userId);
    }
}
