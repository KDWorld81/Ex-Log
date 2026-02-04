package com.exlog.exlog.domain.exercise.controller;

import com.exlog.exlog.domain.exercise.dto.ExerciseLogReqDto;
import com.exlog.exlog.domain.exercise.service.ExerciseLogService;
import com.exlog.exlog.security.userdetail.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exercise-log")
public class ExerciseLogController {

    private final ExerciseLogService exerciseLogService;

    @PostMapping
    public ResponseEntity<Long> createExerciseLog(

            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid @RequestBody ExerciseLogReqDto exerciseLogReqDto
    ) {

        Long savedLogId = exerciseLogService.exerciseLogging(
                customUserDetails.getUser(),
                exerciseLogReqDto
        );

        return ResponseEntity.ok(savedLogId);
    }
}
