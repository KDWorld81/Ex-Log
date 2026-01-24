package com.exlog.exlog.domain.exercise.controller;

import com.exlog.exlog.domain.exercise.dto.CategoryResDto;
import com.exlog.exlog.domain.exercise.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }
}
