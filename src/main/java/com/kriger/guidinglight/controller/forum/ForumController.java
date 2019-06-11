package com.kriger.guidinglight.controller.forum;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ForumController {

    @GetMapping("/forum")
    public String forum() {
        return "forum/forum";
    }
}
