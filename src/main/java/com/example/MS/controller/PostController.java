package com.example.MS.controller;

import com.example.MS.dto.PostDTO;
import com.example.MS.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO.PostResponse> getPost(@PathVariable Long postId) {
        PostDTO.PostResponse postResponse = postService.getPostById(postId);
        return ResponseEntity.ok(postResponse);
    }

    @PutMapping("/posts/{id}/like")
    public ResponseEntity<PostDTO.LikePostResponse> likePost(@PathVariable Long id) {
        PostDTO.LikePostResponse response = postService.updateLikes(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/posts/{id}/dislike")
    public ResponseEntity<PostDTO.DislikePostResponse> dislikePost(@PathVariable Long id) {
        PostDTO.DislikePostResponse response = postService.updateDislikes(id);
        return ResponseEntity.ok(response);
    }
}
