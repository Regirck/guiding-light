package com.kriger.guidinglight.controller.forum;

import com.kriger.guidinglight.model.json.QuestionForTheForum;
import com.kriger.guidinglight.service.ForumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ForumApiController {

    @Autowired
    private ForumService forumService;

    @GetMapping("/forum/questions")
    public ResponseEntity<List<QuestionForTheForum>> getQuestions() {
        List<QuestionForTheForum> questions = forumService.getAllQuestionSort();
        if (questions == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}
