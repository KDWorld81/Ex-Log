package com.exlog.exlog.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LogPageController {

    @GetMapping("/exercise/category/{categoryId}/log/{exerciseId}")
    public String exerciseLogPage(
            @PathVariable Long categoryId, // categoryId: 뒤로 가기 버튼용
            @PathVariable Long exerciseId // exerciseId: 실제 어떤 운동인지 식별용
    ) {
        return "exercise/exerciselog";
    }
}