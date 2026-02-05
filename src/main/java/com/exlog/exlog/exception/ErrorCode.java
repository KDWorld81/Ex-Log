package com.exlog.exlog.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 회원가입
    DUPLICATE_EMAIL(409 , "이미 존재하는 회원(이메일) 입니다."),

    // 로그인
    USER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    PASSWORD_NOT_MATCH(404, "비밀번호가 틀렸습니다."),

    // 권한
    FORBIDDEN_ACCESS(403, "접근 권한이 없습니다."),

    // 400 기본
    MISSING_INPUT_VALUE(400, "필수 입력값이 누락되었습니다."),
    INVALID_TYPE_VALUE(400, "입력 타입이 올바르지 않습니다."),
    INVALID_PARAMETER(400, "파라미터 값을 확인해주세요."),

    // 500 서버
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다. 관리자에게 문의하세요."),

    // ExerciseLog (운동기록)
    EXERCISE_NOT_FOUND(404, "존재하지 않는 운동입니다."),
    INVALID_INPUT_TIME(400, "유산소 운동은 시간은 필수 입력 입니다."),
    INVALID_INPUT_REPS_SETS(400,"근력운동은 세트와 횟수 필수 입력 입니다.");

    private final int status;
    private final String message;
}
