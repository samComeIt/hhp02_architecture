package hhplus.architecture.demo.controller.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResponseDTO extends RuntimeException{
    public ResponseDTO(String message)
    {
        super(message);
    }
}