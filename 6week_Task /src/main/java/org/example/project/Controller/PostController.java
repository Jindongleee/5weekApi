package org.example.project.Controller;

import org.example.project.domain.Member;
import org.example.project.domain.Post;
import org.example.project.dto.MemberDto;
import org.example.project.dto.PostDto;
import org.example.project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> registerPost(@RequestBody PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        Post registeredPost = postService.registerPost(post);
        return ResponseEntity.ok(PostDto.from(registeredPost));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostDto> UpdatePost(@PathVariable(name="id") Long id, @RequestBody PostDto postDto){
        try{
            Post updatePost = postService.updatePost(id,postDto);
            return ResponseEntity.ok(PostDto.from(updatePost));
        }
        catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable(name="id") Long id){
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
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

    @GetMapping("/{id}")
    public ResponseEntity <PostDto> getPostById(@PathVariable(name="id") Long id){
        Optional<Post> postOptional= postService.getPostById(id);
        if(postOptional.isPresent()){
            PostDto dto = PostDto.from(postOptional.get());
            return ResponseEntity.ok(dto);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
