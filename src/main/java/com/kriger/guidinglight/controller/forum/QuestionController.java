package com.kriger.guidinglight.controller.forum;

import com.kriger.guidinglight.model.forum.Question;
import com.kriger.guidinglight.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String getQuestion(@PathVariable("id") Long id, Model model) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);

        //TODO answer and tags need

        return "forum/question";
    }

    @GetMapping("/new_answer/{id}")
    public String createNewAnswer(@PathVariable("id") Long questionId, Model model) {
        model.addAttribute("questionId", questionId);
        return "forum/new-answer";
    }

    @PostMapping("/new_answer/{id}")
    public String saveAnswer(@PathVariable("id") Long questionId,
                             @RequestParam("answer") String answer) {
        questionService.saveAnswerForTheQuestion(questionId, answer);
        return "redirect:/question/" + questionId;
    }

}
