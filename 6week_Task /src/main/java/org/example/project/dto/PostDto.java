package org.example.project.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.project.domain.Post;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto {
    private Long id;
    private MemberDto member;
    private String title;
    private String content;
    private LocalDateTime postDate;

    public static PostDto from(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setPostDate(post.getPostDate());
        if(post.getMember()!=null){
            postDto.setMember(MemberDto.from(post.getMember()));
        }

        //생성된 객체 반환
        return postDto;
    }
}
