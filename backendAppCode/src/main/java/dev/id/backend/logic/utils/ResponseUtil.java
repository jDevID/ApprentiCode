package dev.id.backend.logic.utils;

import dev.id.backend.web.dtos.ResponseDto;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class ResponseUtil {

    public static <U> ResponseDto<U> buildResponse(LocalDateTime timeStamp, HttpStatus status, String message, Map<String, String> errors, U data) {
        return ResponseDto.<U>builderWithGeneric()
                .timeStamp(timeStamp)
                .status(status)
                .message(message)
                .errors(errors)
                .data(data)
                .build();
    }
}
