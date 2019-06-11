package com.kriger.guidinglight.repository.forum;

import com.kriger.guidinglight.model.forum.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {


}
