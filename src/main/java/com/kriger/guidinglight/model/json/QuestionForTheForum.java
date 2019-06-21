package com.kriger.guidinglight.model.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionForTheForum {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime submissionTime;
    private Long userId;

}
