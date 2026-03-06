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

    /**
     * 카테고리별 운동 종목 목록 조회
     * 특정 카테고리에 속한 모든 운동 종목을 DTO 리스트로 변환하여 반환
     *
     * @param categoryId 조회할 카테고리 식별자(ID)
     * @return 해당 카테고리의 운동 종목 DTO 리스트
     */
    public List<ExerciseResDto> findExerciseByCategory(Long categoryId) {
        return exerciseRepository.findByCategory_CategoryId(categoryId).stream()
                .map(ExerciseResDto::fromEntity)
                .collect(Collectors.toList());
    }
}
