package hhplus.architecture.demo.controller.dto;

import hhplus.architecture.demo.domain.Enroll;
import lombok.*;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDTO
{
    private Long userId;
    private Long lectureId;

    @Builder
    public RequestDTO(Long userId, Long lectureId) {
        this.userId = userId;
        this.lectureId = lectureId;
    }
}
