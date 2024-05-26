package org.example.project.dto;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.example.project.domain.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class CommentDto {
    private Long id;
    private MemberDto member;
    private PostDto post;
    private String content;
    private LocalDateTime commentDate;

    public static CommentDto from (Comment comment){
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setContent(comment.getContent());
            commentDto.setCommentDate(comment.getCommentDate());
            if(comment.getMember()!=null){
                commentDto.setMember(MemberDto.from(comment.getMember()));
            }
            if(comment.getPost() !=null){
            commentDto.setPost(PostDto.from(comment.getPost()));
            }


            return commentDto;
    }

}
