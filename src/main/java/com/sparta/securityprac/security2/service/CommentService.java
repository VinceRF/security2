package com.sparta.securityprac.security2.service;

import com.sparta.securityprac.security2.model.Board;
import com.sparta.securityprac.security2.model.Comment;
import com.sparta.securityprac.security2.model.User;
import com.sparta.securityprac.security2.repository.BoardRepository;
import com.sparta.securityprac.security2.repository.CommentRepository;
import com.sparta.securityprac.security2.requestdto.CommentRequestDto;
import com.sparta.securityprac.security2.requestdto.CommentResponseDto;
import com.sparta.securityprac.security2.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void addComment(long boardId, UserDetailsImpl userDetails, CommentRequestDto requestDto) {

        User user = userDetails.getUser();

        Board foundBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("읔"));

        // 댓글 저장
        Comment comment = Comment.builder()
                .content(requestDto.getContent())
                .board(foundBoard)
                .user(user)
                .build();
        commentRepository.save(comment);


    }

    public List<CommentResponseDto> getCommentList(long boardId) {

        Board foundBoard = boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("읔"));

        List<Comment> foundCommentList = commentRepository.findAllByBoard(foundBoard);

        // 반환 DTO에 담기
        List<CommentResponseDto> responseDtoList = new ArrayList<>();
        for (Comment comment : foundCommentList) {
            CommentResponseDto responseDto = new CommentResponseDto(comment);
            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }

    //코멘트 삭제
    @Transactional
    public void deleteComment(long commentId, UserDetailsImpl userDetails) {

        User user = userDetails.getUser();

        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(()->new IllegalArgumentException("엨"));

        if (!user.getUserId().equals(foundComment.getUser().getUserId())) {
            throw new IllegalArgumentException("엨");
        }

        // 댓글 삭제
        commentRepository.delete(foundComment);
    }

    //댓글 수정
    @Transactional
    public void editComment(long commentId, UserDetailsImpl userDetails, CommentRequestDto requestDto) {


        User user = userDetails.getUser();

        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(()->new IllegalArgumentException("엨"));

        if (!user.getUserId().equals(foundComment.getUser().getUserId())) {
            throw new IllegalArgumentException("엨");
        }

        foundComment.editComment(requestDto.getContent());
    }
}
