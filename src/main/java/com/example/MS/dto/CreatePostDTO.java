package com.example.MS.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostDTO {
    private String title;
    private String content;
    private String author;

}