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

    public List<CategoryResDto> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryResDto(category.getCategoryId(), category.getCategoryName()))
                .collect(Collectors.toList());
    }
}
