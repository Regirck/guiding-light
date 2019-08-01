package com.kriger.guidinglight.service;

import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.model.forum.Question;
import com.kriger.guidinglight.repository.UserRepository;
import com.kriger.guidinglight.repository.forum.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class ForumService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    private List<Question> questions = new ArrayList<>();

    public void buildQuestions() {
        List<Question> questionRepositoryAll = questionRepository.findAll();

        if (questions.isEmpty()) {
            questions.addAll(questionRepositoryAll);
            questions.sort(Comparator.comparing(Question::getSubmissionTime).reversed());
        }
    }

    public Page<Question> findPagination(Pageable pageable) {

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Question> list;

        if (questions.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, questions.size());
            list = questions.subList(startItem, toIndex);
        }

        Page<Question> questionPage = new PageImpl<>(
                list, PageRequest.of(currentPage, pageSize), questions.size());

        return questionPage;
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

            questions.add(0, question);
        }
    }

}
