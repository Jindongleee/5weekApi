package org.example.project.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {

    //Post 테이블의 기본키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    // 외래키를 지정, member_id(멤버 테이블의 기본키인 ID)가 외래키로 사용됨
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    private String title;
    private String content;

    @CreationTimestamp
    private LocalDateTime postDate;

    @OneToMany(mappedBy="post")
    private List<Comment> comments= new ArrayList<>();

}
