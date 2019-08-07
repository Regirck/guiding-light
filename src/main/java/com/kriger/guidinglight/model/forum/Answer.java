package com.kriger.guidinglight.model.forum;

import com.kriger.guidinglight.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String answer;

    private LocalDateTime submissionTime;

    @ManyToOne
    private Question question;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Set<AnswerVote> votes = new HashSet<>();
}
