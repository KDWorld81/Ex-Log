package com.exlog.exlog.domain.exercise.dto;

import com.exlog.exlog.domain.exercise.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CategoryResDto {

    private final Long categoryId;
    private final String categoryName;

    public static CategoryResDto fromEntity(Category category) {
        return CategoryResDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .build();

    }
}
