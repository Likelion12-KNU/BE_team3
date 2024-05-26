package com.example.MS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class CommentDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResponse {
        private List<Comment> comments;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Comment {
            private String author;
            private String content;
            private LocalDateTime createdAt;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateCommentRequest {
        private String author;
        private String content;
    }

}
