package com.sparta.securityprac.security2.controller;

import com.sparta.securityprac.security2.requestdto.CommentRequestDto;
import com.sparta.securityprac.security2.requestdto.CommentResponseDto;
import com.sparta.securityprac.security2.security.UserDetailsImpl;
import com.sparta.securityprac.security2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

  private final CommentService commentService;


    @PostMapping("/comments/write/{boardId}")
    public void addComment(
            @PathVariable long boardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CommentRequestDto requestDto
    )
    {
        commentService.addComment(boardId, userDetails, requestDto);
    }

    @GetMapping("/comments/list/{boardId}")
    public List<CommentResponseDto> getCommentList(
            @PathVariable long boardId
            ){
      return commentService.getCommentList(boardId);
    }


}
