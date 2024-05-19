package org.example.project.Controller;

import org.example.project.domain.Comment;
import org.example.project.dto.CommentDto;
import org.example.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        comment.setContent(comment.getContent());

        Comment registeredComment = commentService.registerComment(comment);
        return ResponseEntity.ok(CommentDto.from(registeredComment));
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


}
