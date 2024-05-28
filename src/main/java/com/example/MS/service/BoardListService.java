package com.example.MS.service;

import com.example.MS.dto.BoardListDTO.BoardListResponse;
import com.example.MS.model.BoardList; // BoardList 모델 클래스 import
import com.example.MS.repository.BoardListRepository; // BoardListRepository import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardListService {

    private final BoardListRepository boardListRepository;

    @Autowired
    public BoardListService(BoardListRepository boardListRepository) {
        this.boardListRepository = boardListRepository;
    }

    public List<BoardListResponse> getBoardList() {
        List<BoardList> boardList = boardListRepository.findAll(); // 게시글 목록을 DB에서 조회하는 메소드 호출
        return boardList.stream()
                .map(this::mapToBoardListResponse)
                .collect(Collectors.toList());
    }

    private BoardListResponse mapToBoardListResponse(BoardList boardList) {
        BoardListResponse response = new BoardListResponse();
        response.setId(boardList.getId());
        response.setTitle(boardList.getTitle());
        response.setContent(boardList.getContent());
        response.setAuthor(boardList.getAuthor());
        response.setCreatedAt(boardList.getCreatedAt());
        response.setLikes(boardList.getLikes());
        response.setDislikes(boardList.getDislikes());
        return response;
    }
}
