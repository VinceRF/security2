package com.sparta.securityprac.security2.controller;


import com.sparta.securityprac.security2.model.Board;
import com.sparta.securityprac.security2.repository.BoardRepository;
import com.sparta.securityprac.security2.requestdto.BoardRequestDto;
import com.sparta.securityprac.security2.security.UserDetailsImpl;
import com.sparta.securityprac.security2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @PostMapping("/boards/write")
    public Board writeBoard(@RequestBody BoardRequestDto requestDto,
                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Board board = new Board(requestDto,userDetails);
        return boardRepository.save(board);
    }

    @PutMapping("/boards/{boardId}")
    public void editBoard(
            @PathVariable long boardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody BoardRequestDto requestDto
    ) {
        boardService.editBoard(boardId, userDetails, requestDto);
    }


    @GetMapping("/boards")
    public List<Board> getBoards() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시글 특정 조회
    @GetMapping("/boards/detail/{boardId}")
    public Board getContents(@PathVariable Long boardId) {
        Board board =  boardRepository.findById(boardId).orElseThrow(
                ()->new IllegalArgumentException("contentsId가 존재하지 않습니다."));
        return board;
    }

    @DeleteMapping("/boards/{boardId}")
    public void deleteBoard(
            @PathVariable long boardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        boardService.deleteBoard(boardId, userDetails);
    }
}
