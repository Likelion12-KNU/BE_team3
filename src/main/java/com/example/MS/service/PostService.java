package com.example.MS.service;

import com.example.MS.dto.PostDTO;
import com.example.MS.exception.PostNotFoundException;
import com.example.MS.model.Post;
import com.example.MS.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    

    public PostDTO.PostResponse getPostById(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            return new PostDTO.PostResponse(
                    post.getTitle(),
                    post.getContent(),
                    post.getAuthor(),
                    post.getCreatedAt(),
                    post.getUpdatedAt(),
                    post.getLikes(),
                    post.getDislikes()
            );
        } else {
            throw new PostNotFoundException("Post not found with id " + id);
        }
    }

    public PostDTO.LikePostResponse updateLikes(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found with id " + id));
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
        return new PostDTO.LikePostResponse(post.getLikes());
    }

    public PostDTO.DislikePostResponse updateDislikes(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found with id " + id));
        post.setDislikes(post.getDislikes() + 1);
        postRepository.save(post);
        return new PostDTO.DislikePostResponse(post.getDislikes());
    }

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
