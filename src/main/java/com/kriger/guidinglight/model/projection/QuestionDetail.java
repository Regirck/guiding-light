package com.kriger.guidinglight.model.projection;

import com.kriger.guidinglight.model.forum.Answer;
import com.kriger.guidinglight.model.forum.Comment;
import com.kriger.guidinglight.model.forum.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDetail {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime submissionTime;

    private List<Tag> tags = new ArrayList<>();

    private List<Comment> comments = new ArrayList<>();

    private List<Answer> answers = new ArrayList<>();


}
