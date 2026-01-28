package com.exlog.exlog.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Map;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResDto {
    private final int status;
    private final String message;
    private final Map<String, String> validation;

    // 일반 에러용 생성자
    public ErrorResDto(String message,int status) {
        this.status = status;
        this.message = message;
        this.validation = null;
    }

    // Validation 에러용 생성자
    public ErrorResDto(String message, int status,  Map<String, String> validation) {
        this.status = status;
        this.message = message;
        this.validation = validation;
    }

}