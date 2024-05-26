package org.example.project.Controller;

import org.apache.catalina.valves.rewrite.RewriteCond;
import org.example.project.domain.Comment;
import org.example.project.dto.CommentDto;
import org.example.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> registerComment(@RequestBody CommentDto commentDto){
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());

        Comment registeredComment = commentService.registerComment(comment);
        return ResponseEntity.ok(CommentDto.from(registeredComment));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentDto> UpdateComment(@PathVariable(name="id") Long id,@RequestBody CommentDto commentDto){
        try{
            Comment updateComment = commentService.updateComment(id,commentDto);
            return ResponseEntity.ok(CommentDto.from(updateComment));
            }
        catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable(name="id") Long id){
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity <List<CommentDto>> getAllComments(){
        List<Comment> comments = commentService.getAllComments();
        List<CommentDto> commentDtos = new ArrayList<>();
        for(Comment comment:comments){
            CommentDto dto = CommentDto.from(comment);
            commentDtos.add(dto);
        }
        return ResponseEntity.ok(commentDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name="id") Long id){
        Optional<Comment> commentOptional = commentService.getCommentById(id);
        if(commentOptional.isPresent()){
            CommentDto dto=CommentDto.from(commentOptional.get());
            return ResponseEntity.ok(dto);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }




}
