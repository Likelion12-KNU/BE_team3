package com.example.MS.service;

import com.example.MS.dto.PostDTO;
import com.example.MS.exception.PostNotFoundException;
import com.example.MS.model.Post;
import com.example.MS.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
