package com.kriger.guidinglight.service;

import com.kriger.guidinglight.model.User;
import com.kriger.guidinglight.model.forum.Question;
import com.kriger.guidinglight.model.projection.QuestionToTheForum;
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
import java.util.stream.IntStream;

@Slf4j
@Service
public class ForumService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    private List<QuestionToTheForum> questions = new ArrayList<>();

    public void buildQuestions() {
        List<Question> questionRepositoryAll = questionRepository.findAll();

        if (questions.isEmpty()) {
            IntStream.range(0, questionRepositoryAll.size()).forEach(q ->
                    questions.add(
                    QuestionToTheForum.builder()
                            .id(questionRepositoryAll.get(q).getId())
                            .title(questionRepositoryAll.get(q).getTitle())
                            .content(questionRepositoryAll.get(q).getContent())
                            .answerSize(questionRepositoryAll.get(q).getAnswers().size())
                            .submissionTime(questionRepositoryAll.get(q).getSubmissionTime())
                            .build()));
            log.info("It's ran!");
            questions.sort(Comparator.comparing(QuestionToTheForum::getSubmissionTime).reversed());
        }

        for (QuestionToTheForum question : questions) {
            log.info(question.getTitle());
        }
    }

    public Page<QuestionToTheForum> findPagination(Pageable pageable) {

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<QuestionToTheForum> list;

        if (questions.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, questions.size());
            list = questions.subList(startItem, toIndex);
        }

        Page<QuestionToTheForum> questionPage = new PageImpl<>(
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

            questions.add(0,
                    QuestionToTheForum.builder()
                            .id(question.getId())
                            .title(question.getTitle())
                            .content(question.getContent())
                            .answerSize(question.getAnswers().size())
                            .submissionTime(question.getSubmissionTime())
                            .build());
        }
    }

    public Question findQuestion(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        return question.orElse(null);
    }
}
