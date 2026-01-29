package com.exlog.exlog.domain.exercise.repository;

import com.exlog.exlog.domain.exercise.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByCategory_CategoryId(Long categoryId);

}
