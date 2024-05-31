package com.example.MS.service;

import com.example.MS.dto.PostDTO;
import com.example.MS.exception.PostNotFoundException;
import com.example.MS.model.Post;
import com.example.MS.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

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

}
