package org.example.project.service;

import jakarta.transaction.Transactional;
import org.example.project.Repository.MemberRepository;
import org.example.project.Repository.PostRepository;
import org.example.project.domain.Member;
import org.example.project.domain.Post;
import org.example.project.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Transactional
    public Post registerPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id){
        postRepository.deleteById(id);
    }

    @Transactional
    public Post updatePost(Long id, PostDto postDto){
        Optional<Post> postOptional = postRepository.findById(id);
        if(postOptional.isPresent()){
            Post post = postOptional.get();
            if(postDto.getTitle() != null){
                post.setTitle(postDto.getTitle());
            }
            if(postDto.getContent()!=null){
                post.setContent(postDto.getContent());
            }
            return postRepository.save(post);
        }
        else{
            throw new RuntimeException("Post     not found with id "+id);
        }
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    //member_id 받아오기
    /*public Optional<Post>  getPostByMember(Member member){
        return postRepository.findById(member.getId());
    }*/
}
