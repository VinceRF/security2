package com.sparta.securityprac.security2.service;

import com.sparta.securityprac.security2.model.Board;
import com.sparta.securityprac.security2.model.Comment;
import com.sparta.securityprac.security2.model.User;
import com.sparta.securityprac.security2.repository.BoardRepository;
import com.sparta.securityprac.security2.requestdto.BoardRequestDto;
import com.sparta.securityprac.security2.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long update(Long boardId, BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        board.update(requestDto,userDetails);
        return board.getBoardId();
    }


    //게시글 삭제
    @Transactional
    public void deleteBoard(long commentId, UserDetailsImpl userDetails) {

        User user = userDetails.getUser();

        Board foundBoard = boardRepository.findById(commentId)
                .orElseThrow(()->new IllegalArgumentException("엨"));

        if (!user.getUsername().equals(foundBoard.getWriter())){
            throw new IllegalArgumentException("엨");
        }

        // 게시글
        boardRepository.delete(foundBoard);
    }

    // 게시글 수정
    @Transactional
    public void editBoard(long boardId, UserDetailsImpl userDetails, BoardRequestDto requestDto) {

        User user = userDetails.getUser();

        Board foundBoard = boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("엨"));

        if (!foundBoard.getWriter().equals(user.getUsername())) {
            throw new IllegalArgumentException("엨");
        };

        foundBoard.update(requestDto,userDetails);
        boardRepository.save(foundBoard);
    }




}
