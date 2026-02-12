package com.exlog.exlog.pagecontroller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyTitlePageController {

    @GetMapping("/mypage/mytitle")
    public String titleLibraryPage() {
        return "mypage/mytitle";
    }
}
