package com.example.MS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BoardListDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardListResponse {
        private Long id;
        private String title;
        private String content;
        private String author;
        private LocalDateTime createdAt;
        private int likes;
        private int dislikes;
    }
}
