package com.kriger.guidinglight.repository.forum;

import com.kriger.guidinglight.model.forum.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
