package com.exlog.exlog.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPagePageController {

    @GetMapping("/mypage")
    public String myPage() {
        return "mypage/mypage";
    }
}
