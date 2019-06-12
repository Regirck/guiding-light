package com.kriger.guidinglight.service;

import com.kriger.guidinglight.model.forum.Question;
import com.kriger.guidinglight.repository.forum.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ForumService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestionSort() {
        List<Question> questions = questionRepository.findAll();
        questions.sort((Comparator.comparing(Question::getSubmissionTime)));
        return questions;
    }
}
