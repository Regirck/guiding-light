package com.kriger.guidinglight.controller.forum;

import com.kriger.guidinglight.model.forum.Question;
import com.kriger.guidinglight.model.projection.QuestionToTheForum;
import com.kriger.guidinglight.service.ForumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
public class ForumController {

    @Autowired
    private ForumService forumService;

    @GetMapping("/forum")
    public String forum(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {

        forumService.buildQuestions();

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(25);

        model.addAttribute("currentPage", currentPage);

        Page<QuestionToTheForum> questionPage =
                forumService.findPagination(PageRequest.of(currentPage -1, pageSize));

        model.addAttribute("questionPage", questionPage);

        return "forum/forum";
    }

    @GetMapping("/forum/new_question")
    public String createNewQuestion(Model model){
        Question question = new Question();
        model.addAttribute("question", question);
        return "forum/new-question";
    }

    @PostMapping("/forum/new_question")
    public String saveNewQuestion(@ModelAttribute("question") @Valid Question question){
        forumService.saveQuestion(question);
        return "redirect:/forum";
    }

    @GetMapping("/question/{id}")
    public String getQuestion(@PathVariable("id") Long id, Model model) {
        Question question = forumService.findQuestion(id);
        // TODO create projection
        model.addAttribute("question", question);
        return "forum/question";
    }
}
