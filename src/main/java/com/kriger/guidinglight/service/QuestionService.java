package com.kriger.guidinglight.service;

import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.model.forum.Answer;
import com.kriger.guidinglight.model.forum.Question;
import com.kriger.guidinglight.repository.UserRepository;
import com.kriger.guidinglight.repository.forum.AnswerRepository;
import com.kriger.guidinglight.repository.forum.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public Question getQuestion(long id) {
        Question question = questionRepository.findById(id);
        return question;
    }

    public List<Answer> getAnswers() {
        List<Answer> answers = answerRepository.findAll();
        if (!answers.isEmpty()) {
            answers.sort(Comparator.comparing(Answer::getSubmissionTime).reversed());
        }
        return answers;
    }

    public void saveAnswerForTheQuestion(long questionId, String answer) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        Question question = getQuestion(questionId);
        Answer buildAnswer = Answer.builder()
                .answer(answer)
                .question(question)
                .submissionTime(LocalDateTime.now())
                .user(user)
                .build();
        answerRepository.save(buildAnswer);
    }

}
