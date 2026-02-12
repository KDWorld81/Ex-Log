package com.exlog.exlog.domain.exercise.dto;

import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.exercise.entity.Category;
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
    private final Long reps;
    private final Long sets;
    private final Integer minutes;
    private final Double distance;

    public ExerciseLog toEntity(User user, Exercise exercise, Category category) {
        return ExerciseLog.builder()
                .exercise(exercise)
                .category(category)
                .user(user)
                .reps(this.reps)
                .sets(this.sets)
                .distance(this.distance)
                .minutes(this.minutes)
                .build();
    }

}
