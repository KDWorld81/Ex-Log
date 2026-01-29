package com.exlog.exlog.pagecontroller;

import com.exlog.exlog.domain.exercise.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CategoryPageController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public String categoryPage(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        return "exercise/category";
    }
}
