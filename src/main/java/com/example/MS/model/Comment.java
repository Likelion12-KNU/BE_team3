package com.example.MS.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", nullable = false, foreignKey = @ForeignKey(name = "fk_comments_posts", foreignKeyDefinition = "FOREIGN KEY (postId) REFERENCES posts(id) ON DELETE CASCADE"))
    private Post post;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public Comment(Post post, String author, String content, LocalDateTime createdAt) {
        this.post = post;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
    }

}
