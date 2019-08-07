package com.kriger.guidinglight.model.forum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "answer_votes")
public class AnswerVote {

    @Id
    @GeneratedValue
    private Long id;

    private Integer positiveVoteUserId;

    private Integer negativeVoteUserId;

    @ManyToOne
    private Answer answer;
}
