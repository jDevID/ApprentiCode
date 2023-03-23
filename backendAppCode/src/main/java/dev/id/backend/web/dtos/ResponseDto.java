package dev.id.backend.web.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {
    private LocalDateTime timeStamp;
    private HttpStatus status;
    private String message;
    private Map<String, String> errors;
    private T data;

    private ResponseDto(CustomResponseDtoBuilder<T> builder) {
        this.timeStamp = builder.timeStamp;
        this.status = builder.status;
        this.message = builder.message;
        this.errors = builder.errors;
        this.data = builder.data;
    }

    public static <U> CustomResponseDtoBuilder<U> builderWithGeneric() {
        return new CustomResponseDtoBuilder<>();
    }

    public static class CustomResponseDtoBuilder<U> {
        private LocalDateTime timeStamp;
        private HttpStatus status;
        private String message;
        private Map<String, String> errors;
        private U data;

        public CustomResponseDtoBuilder<U> timeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public CustomResponseDtoBuilder<U> status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public CustomResponseDtoBuilder<U> message(String message) {
            this.message = message;
            return this;
        }

        public CustomResponseDtoBuilder<U> errors(Map<String, String> errors) {
            this.errors = errors;
            return this;
        }

        public CustomResponseDtoBuilder<U> data(U data) {
            this.data = data;
            return this;
        }

        public ResponseDto<U> build() {
            return new ResponseDto<>(this);
        }
    }
}