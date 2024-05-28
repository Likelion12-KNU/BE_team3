package com.example.MS.service;

//import com.example.MS.controller.CreatePostController;
import com.example.MS.model.Post;
import com.example.MS.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

//import java.util.Optional;
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    //게시글 생성 메서드
    public Post createPost(Post post){
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    //게시글 삭제 메서드
    public void deletePost(Long id){
        if(postRepository.existsById(id)){
            postRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("Post with "+ id +" does not exist.");
        }
    }

    // 게시글 삭제(비밀번호 사용 ver.)
/*    public void deletePost(Long id, String deletePassword){
        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            if(post.getDeletePassword().equals(deletePassword)) {
                postRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("Incorrect password");
            }
        } else {
            throw new IllegalArgumentException("Post with "+ id +" does not exist.");
        }
    }
    // id로 게시글을 찾는 메서드
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }*/
}
