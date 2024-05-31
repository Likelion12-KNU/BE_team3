package com.example.MS.controller;

import com.example.MS.dto.CreatePostDTO;
import com.example.MS.dto.PostDTO;
import com.example.MS.model.Post;
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

    // 게시글 작성
    // @param post 사용자가 작성한 게시글 정보
    // @return 생성된 게시글과 200 OK 응답
    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody CreatePostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAuthor(postDTO.getAuthor());
        Post createdPost = postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }

    // 게시글 삭제
    // @param id 삭제할 게시글의 id
    // @return 성공적으로 삭제 시 204 No Content 응답, 게시글 미존재 시 404 Not Found 응답
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        try {
            postService.deletePost(postId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 게시글 삭제(비밀번호 사용 ver.)
/*    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @RequestParam String deletePassword) {
        try {
            postService.deletePost(postId, deletePassword);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }*/
}
