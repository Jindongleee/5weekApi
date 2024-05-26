package org.example.project.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {

    //primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="comment_id")
    private Long id;

    //member_id(FK)
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    //post_id(FK)
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column
    private String content;

    @CreationTimestamp
    private LocalDateTime commentDate;
}
