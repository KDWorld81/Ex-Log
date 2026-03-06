package com.exlog.exlog.domain.exercise.service;

import com.exlog.exlog.domain.exercise.dto.CategoryResDto;
import com.exlog.exlog.domain.exercise.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * 전체 운동 카테고리 목록 조회
     * 등록된 모든 운동 카테고리를 엔티티에서 DTO 리스트로 변환하여 반환
     *
     * @return 운동 카테고리 DTO 리스트
     */
    public List<CategoryResDto> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryResDto::fromEntity)
                .collect(Collectors.toList());
    }
}
