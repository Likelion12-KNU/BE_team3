package com.example.MS.controller;

import com.example.MS.dto.BoardListDTO.BoardListResponse;
import com.example.MS.service.BoardListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardListController {

    private final BoardListService boardListService;

    @Autowired
    public BoardListController(BoardListService boardListService) {
        this.boardListService = boardListService;
    }

    @GetMapping
    public List<BoardListResponse> getBoardList() {
        return boardListService.getBoardList(); // 게시글 목록 조회 서비스 호출
    }
}
