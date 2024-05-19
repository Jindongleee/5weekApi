package org.example.project.Controller;

import org.example.project.domain.Post;
import org.example.project.dto.MemberDto;
import org.example.project.dto.PostDto;
import org.example.project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<PostDto> registerPost(@RequestBody PostDto postdto){
        Post post = new Post();
        post.setTitle(post.getTitle());
        post.setContent(post.getContent());

        Post registeredPost = postService.registerPost(post);
        return ResponseEntity.ok(PostDto.from(registeredPost));
    }

    @GetMapping
    public ResponseEntity <List<PostDto>> getAllPosts(){
        List<Post> posts= postService.getAllPosts();
        List<PostDto> postDtos = new ArrayList<>();
        for(Post post:posts){
            PostDto dto = PostDto.from(post);
            postDtos.add(dto);
        }
        return ResponseEntity.ok(postDtos);
    }
}
