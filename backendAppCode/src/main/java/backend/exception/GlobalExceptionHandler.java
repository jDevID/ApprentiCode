package exceptions;

import backend.util.ResponseUtil;
import domain.dto.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler<DTO> {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDto<Optional<DTO>>> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
        log.debug("Entity not found: {}", request);

        ResponseDto<Optional<DTO>> responseDto = ResponseUtil.buildResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND, e.getMessage(), null, Optional.empty());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Optional<DTO>>> handleGeneralException(Exception e, WebRequest request) {
        log.error("Unexpected exception: {}", request, e);

        ResponseDto<Optional<DTO>> responseDto = ResponseUtil.buildResponse(LocalDateTime.now(), INTERNAL_SERVER_ERROR, e.getMessage(), null, Optional.empty());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Optional<DTO>>> handleValidationException(MethodArgumentNotValidException e, WebRequest request) {
        log.debug("Validation failed: {}", request);

        Map<String, String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .filter(fieldError -> fieldError.getDefaultMessage() != null)
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        ResponseDto<Optional<DTO>> responseDto = ResponseUtil.buildResponse(LocalDateTime.now(), BAD_REQUEST, "Validation failed", errors, Optional.empty());
        return ResponseEntity.status(BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(EntityDeleteException.class)
    public ResponseEntity<ResponseDto<Optional<DTO>>> handleEntityDeleteException(EntityDeleteException e, WebRequest request) {
        log.debug("Entity delete failed: {}", request);

        ResponseDto<Optional<DTO>> responseDto = ResponseUtil.buildResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST, e.getMessage(), null, Optional.empty());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseDto<Optional<DTO>>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, WebRequest request) {
        log.warn("HTTP method not supported: {}", request);

        ResponseDto<Optional<DTO>> responseDto = ResponseUtil.buildResponse(LocalDateTime.now(), HttpStatus.METHOD_NOT_ALLOWED, e.getMessage(), null, Optional.empty());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(responseDto);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ResponseDto<Optional<DTO>>> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, WebRequest request) {
        log.warn("Media type not supported: {}", request);

        ResponseDto<Optional<DTO>> responseDto = ResponseUtil.buildResponse(LocalDateTime.now(), HttpStatus.UNSUPPORTED_MEDIA_TYPE, e.getMessage(), null, Optional.empty());
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(responseDto);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDto<Optional<DTO>>> handleDataIntegrityViolationException(DataIntegrityViolationException e, WebRequest request) {
        log.warn("Data integrity violation: {}", request);

        ResponseDto<Optional<DTO>> responseDto = ResponseUtil.buildResponse(LocalDateTime.now(), HttpStatus.CONFLICT, "Data integrity violation", null, Optional.empty());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDto);
    }

    @ExceptionHandler(EntityOperationException.class)
    public ResponseEntity<ResponseDto<Optional<DTO>>> handleEntityOperationException(EntityOperationException e, WebRequest request) {
        log.debug("Entity operation failed: {}", request);
        HttpStatus httpStatus;
        if (e.getOperationType() == EntityOperationException.OperationType.DELETE) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (e.getOperationType() == EntityOperationException.OperationType.UPDATE) {
            httpStatus = HttpStatus.CONFLICT;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ResponseDto<Optional<DTO>> responseDto = ResponseUtil.buildResponse(LocalDateTime.now(), httpStatus, e.getMessage(), null, Optional.empty());
        return ResponseEntity.status(httpStatus).body(responseDto);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseDto<Optional<DTO>>> handleUsernameNotFoundException(UsernameNotFoundException e, WebRequest request) {
        log.debug("Username not found: {}", request);

        ResponseDto<Optional<DTO>> responseDto = ResponseUtil.buildResponse(LocalDateTime.now(), HttpStatus.UNAUTHORIZED, e.getMessage(), null, Optional.empty());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseDto<Optional<DTO>>> handleBadCredentialsException(BadCredentialsException e, WebRequest request) {
        log.debug("Bad credentials: {}", request);

        ResponseDto<Optional<DTO>> responseDto = ResponseUtil.buildResponse(LocalDateTime.now(), HttpStatus.UNAUTHORIZED, e.getMessage(), null, Optional.empty());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDto<Optional<DTO>>> handleAccessDeniedException(AccessDeniedException e, WebRequest request) {
        log.debug("Access denied: {}", request);

        ResponseDto<Optional<DTO>> responseDto = ResponseUtil.buildResponse(LocalDateTime.now(), HttpStatus.FORBIDDEN, e.getMessage(), null, Optional.empty());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseDto);
    }
}
