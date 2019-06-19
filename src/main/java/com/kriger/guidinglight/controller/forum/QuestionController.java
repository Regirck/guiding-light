package com.kriger.guidinglight.controller.forum;

import com.kriger.guidinglight.model.forum.Question;
import com.kriger.guidinglight.service.ForumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class QuestionController {

    @Autowired
    private ForumService forumService;

    @GetMapping("/question/{id}")
    public String getQuestion(@PathVariable("id") Long id) {
        log.info(String.valueOf(id));

        Question question = forumService.findQuestion(id);
        if (question == null) {
            return "index";
        }
        return "forum/question";
    }

}
