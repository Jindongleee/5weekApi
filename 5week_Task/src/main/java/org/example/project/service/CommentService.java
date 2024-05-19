package org.example.project.service;

import org.example.project.Repository.CommentRepository;
import org.example.project.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    //CommentRepository 인스턴스 자동 주입
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    //조회메서드에서 성능을 최적화 하기 위함(데이터 변경은 없음)
    @Transactional
    public Comment registerComment(Comment comment){
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long id){
        return commentRepository.findById(id);
    }

}
