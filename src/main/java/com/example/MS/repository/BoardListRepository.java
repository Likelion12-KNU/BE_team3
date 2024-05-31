package com.example.MS.repository;

import com.example.MS.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardListRepository extends JpaRepository<Post, Long> {
}