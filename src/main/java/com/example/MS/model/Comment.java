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

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String author;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Builder
    public Comment(Post post, String author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
    }

}
