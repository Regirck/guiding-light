package com.kriger.guidinglight.model.forum;

import com.kriger.guidinglight.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String questions;

    private LocalDateTime submissionTime;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "question" ,cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "question" ,cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Set<Comment> comments = new HashSet<>();

}