package com.kriger.guidinglight.repository.forum;

import com.kriger.guidinglight.model.forum.Question;
import com.kriger.guidinglight.model.forum.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query(value = "SELECT q FROM Tag q WHERE q.question = :question")
    List<Tag> findAllTagsByQuestion(@Param("question") Question question);

}
