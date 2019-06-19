package com.kriger.guidinglight.controller.forum;

import com.kriger.guidinglight.model.forum.Question;
import com.kriger.guidinglight.service.ForumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class ForumController {

    @Autowired
    private ForumService forumService;

    @GetMapping("/forum")
    public String forum() {
//        forumService.getAllQuestionSort();
        return "forum/forum";
    }

    @GetMapping("/new_question")
    public String createNewQuestion(Model model){
        Question question = new Question();
        model.addAttribute("question", question);
        return "forum/new-question";
    }

    @PostMapping("/new_question")
    public String saveNewQuestion(@ModelAttribute("question") @Valid Question question){
        log.info(question.getTitle());
        log.info(question.getQuestion());
        forumService.saveQuestion(question);
        return "forum/forum";
    }
}
