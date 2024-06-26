package hhplus.architecture.demo.controller.dto;

import java.time.LocalDateTime;

public record ResponseDTO(
        Long userId,
        Long lectureId,
        LocalDateTime createdAt
) {

}
