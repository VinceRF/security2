package com.sparta.securityprac.security2.requestdto;

import com.sparta.securityprac.security2.model.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long commentId;

    private String commentwriter, content;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.commentwriter = comment.getUser().getUsername();
        this.content = comment.getContent();
    }
}
