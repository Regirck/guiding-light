package com.kriger.guidinglight.service;

import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.model.forum.Question;
import com.kriger.guidinglight.model.json.QuestionForTheForum;
import com.kriger.guidinglight.repository.UserRepository;
import com.kriger.guidinglight.repository.forum.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ForumService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<QuestionForTheForum> getAllQuestionSort() {
        List<Question> questions = questionRepository.findAll();
        questions.sort((Comparator.comparing(Question::getSubmissionTime)));
        List<QuestionForTheForum> questionsJsonList = new ArrayList<>();
        for (Question question : questions) {
            QuestionForTheForum questionJson = new QuestionForTheForum();
            questionJson.setId(question.getId());
            questionJson.setTitle(question.getTitle());
            questionJson.setContent(question.getContent());
            questionJson.setSubmissionTime(question.getSubmissionTime());
            questionJson.setUserId(question.getUser().getId());
            questionsJsonList.add(questionJson);
        }
        return questionsJsonList;
    }

    public void saveQuestion(Question question) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean authenticated = auth.isAuthenticated();
        if (authenticated) {
            String loggedUserEmail = auth.getName();
            User user = userRepository.findByEmail(loggedUserEmail);
            question.setUser(user);
            question.setSubmissionTime(LocalDateTime.now());
            questionRepository.save(question);
        }
    }

    public Question findQuestion(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        return question.orElse(null);
    }
}
