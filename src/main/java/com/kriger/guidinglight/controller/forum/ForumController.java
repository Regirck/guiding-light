package com.kriger.guidinglight.controller.forum;

import com.kriger.guidinglight.model.forum.Question;
import com.kriger.guidinglight.service.ForumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RequestMapping("/forum")
@Controller
public class ForumController {

    @Autowired
    private ForumService forumService;

    @GetMapping("")
    public String forum(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {

        forumService.buildQuestions();

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(25);

        model.addAttribute("currentPage", currentPage);

        Page<Question> questionPage =
                forumService.findPagination(PageRequest.of(currentPage -1, pageSize));

        model.addAttribute("questionPage", questionPage);

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
        forumService.saveQuestion(question);
        return "redirect:/forum";
    }

}
