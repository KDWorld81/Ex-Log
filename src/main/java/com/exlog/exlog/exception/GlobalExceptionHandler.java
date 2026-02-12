package com.exlog.exlog.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 1. 커스텀 예외
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResDto> handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        ErrorResDto errorDto = new ErrorResDto(
                errorCode.getMessage(),
                errorCode.getStatus()
        );

        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    /**
     * 2. 유효성 검사
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // 프론트가 else 로직으로 가도록 400(Bad Request) 응답 전송
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * 3. 필수 파라미터 누락 (MissingServletRequestParameterException)
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ErrorResDto> handleMissingParam(MissingServletRequestParameterException ex) {
        ErrorCode errorCode = ErrorCode.MISSING_INPUT_VALUE;

        String detailMsg = ex.getParameterName() + " " + errorCode.getMessage();

        ErrorResDto errorDto = new ErrorResDto(detailMsg, errorCode.getStatus());

        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    /**
     * 4. 파라미터 타입 불일치 (MethodArgumentTypeMismatchException)
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResDto> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_TYPE_VALUE;

        String detailMsg = ex.getName() + " " + errorCode.getMessage();

        ErrorResDto errorDto = new ErrorResDto(detailMsg, errorCode.getStatus());

        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    /**
     * 5. 그 외 서버 내부 에러
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResDto> handleServerException(Exception ex) {
        log.error("INTERNAL_SERVER_ERROR", ex);

        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;

        ErrorResDto errorDto = new ErrorResDto(
                errorCode.getMessage(),
                errorCode.getStatus()
        );

        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Void> handleNoResource() {
        return ResponseEntity.notFound().build();
    }

}