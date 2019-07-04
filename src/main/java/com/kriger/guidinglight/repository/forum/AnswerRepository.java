package com.kriger.guidinglight.repository.forum;

import com.kriger.guidinglight.model.forum.Answer;
import com.kriger.guidinglight.model.forum.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query(value = "SELECT q FROM Answer q WHERE q.question = :question")
    List<Answer> findAllAnswersByQuestion(@Param("question") Question question);

}
