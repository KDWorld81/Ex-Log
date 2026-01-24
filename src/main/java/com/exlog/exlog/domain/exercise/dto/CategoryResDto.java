package com.exlog.exlog.domain.exercise.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CategoryResDto {

    private final Long categoryId;
    private final String categoryName;
}
