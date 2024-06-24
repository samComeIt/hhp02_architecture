package hhplus.architecture.demo.controller.dto;

import hhplus.architecture.demo.domain.Enroll;
import lombok.*;

@Getter
@NoArgsConstructor
public class RequestDTO {
    private Long userId;
    private Long lectureId;

//    public static ResponseDTO ofEntity(Enroll enroll)
//    {
//        return ResponseDTO.builder().build().c;
//    }
}
