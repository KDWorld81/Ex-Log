package com.exlog.exlog.domain.exercise.dto;

import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.exercise.entity.Exercise;
import com.exlog.exlog.domain.exercise.entity.ExerciseLog;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class ExerciseLogReqDto {

    @NotNull
    private final Long exerciseId;

    @NotNull
    private final Long reps;

    @NotNull
    private final Long sets;

    public ExerciseLog toEntity(User user, Exercise exercise) {
        return ExerciseLog.builder()
                .exercise(exercise)
                .user(user)
                .reps(this.reps)
                .sets(this.sets)
                .build();
    }

}
