package com.example.MS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostResponse {
        private String title;
        private String content;
        private String author;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private int likes;
        private int dislikes;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LikePostResponse {
        private int likes;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DislikePostResponse {
        private int dislikes;
    }

}
