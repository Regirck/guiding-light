package com.kriger.guidinglight.controller.forum;

import com.kriger.guidinglight.service.ForumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ForumController {

    @Autowired
    private ForumService forumService;

    @GetMapping("/forum")
    public String forum() {
        forumService.getAllQuestionSort();
        return "forum/forum";
    }
}
