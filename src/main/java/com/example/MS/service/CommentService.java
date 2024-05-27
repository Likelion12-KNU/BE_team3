package com.example.MS.service;

import com.example.MS.dto.CommentDTO;
import com.example.MS.exception.PostNotFoundException;
import com.example.MS.model.Comment;
import com.example.MS.model.Post;
import com.example.MS.repository.CommentRepository;
import com.example.MS.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentDTO.CommentResponse getCommentsByPostId(Long postId) {
        if (postRepository.existsById(postId)) {
            List<Comment> comments = commentRepository.findByPostId(postId);
            List<CommentDTO.CommentResponse.Comment> commentResponses = comments.stream()
                    .map(comment -> new CommentDTO.CommentResponse.Comment(
                            comment.getAuthor(),
                            comment.getContent(),
                            comment.getCreatedAt()))
                    .collect(Collectors.toList());
            return new CommentDTO.CommentResponse(commentResponses);
        } else {
            throw new PostNotFoundException("Post not found with id " + postId);
        }
    }

    public void createComment(Long postId, CommentDTO.CreateCommentRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found with id " + postId));
        Comment comment = new Comment(post,
                request.getAuthor(),
                request.getContent());
        commentRepository.save(comment);
    }

}
