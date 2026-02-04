package com.exlog.exlog.domain.exercise.repository;

import com.exlog.exlog.domain.exercise.entity.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {
}
