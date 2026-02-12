package com.exlog.exlog.pagecontroller.exercise;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ExercisePageController {

    @GetMapping("exercise/{categoryId}")
    public String exerciseListPage(@PathVariable Long categoryId) {
        return "exercise/exerciselist";
    }
}
