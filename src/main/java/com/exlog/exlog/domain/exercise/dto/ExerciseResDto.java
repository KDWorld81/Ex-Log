package com.exlog.exlog.domain.exercise.dto;

import com.exlog.exlog.domain.exercise.entity.Exercise;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ExerciseResDto {

    private final Long categoryId;
    private final Long exerciseId;
    private final String exerciseName;

    public static ExerciseResDto fromEntity(Exercise exercise) {
        return ExerciseResDto.builder()
                .categoryId(exercise.getCategory().getCategoryId())
                .exerciseId(exercise.getExerciseId())
                .exerciseName(exercise.getExerciseName())
                .build();
    }
}
