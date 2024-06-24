package hhplus.architecture.demo.controller.dto;


import hhplus.architecture.demo.domain.Enroll;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@RequiredArgsConstructor
public class ResponseDTO {
    private Long userId;
    private Long lectureId;
    private LocalDateTime createdAt;
}
