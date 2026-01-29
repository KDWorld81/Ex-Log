package com.exlog.exlog.domain.exercise.controller;

import com.exlog.exlog.domain.exercise.dto.ExerciseResDto;
import com.exlog.exlog.domain.exercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ExerciseResDto>> getExerciseByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(exerciseService.findExerciseByCategory(categoryId));

    }
}
