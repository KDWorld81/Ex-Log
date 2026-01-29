package com.exlog.exlog.domain.exercise.service;

import com.exlog.exlog.domain.exercise.dto.ExerciseResDto;
import com.exlog.exlog.domain.exercise.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public List<ExerciseResDto> findExerciseByCategory(Long categoryId) {
        return exerciseRepository.findByCategory_CategoryId(categoryId).stream()
                .map(ExerciseResDto::fromEntity)
                .collect(Collectors.toList());
    }
}
