package com.example.MS.controller;

import com.example.MS.dto.CommentDTO;
import com.example.MS.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //댓글 조회 controller
    @GetMapping("/posts/{postId}/comment")
    public ResponseEntity<CommentDTO.CommentResponse> getComments(
            @PathVariable Long postId) {

        CommentDTO.CommentResponse comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    //댓글 작성 controller
    @PostMapping("/posts/{postId}/comment")
    public ResponseEntity<Void> createComment(
            @PathVariable Long postId,
            @RequestBody CommentDTO.CreateCommentRequest request) {

        commentService.createComment(postId, request);
        return ResponseEntity.status(201).build();
    }
}
