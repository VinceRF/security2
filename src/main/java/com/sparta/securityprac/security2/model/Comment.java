package com.sparta.securityprac.security2.model;

import com.sparta.securityprac.security2.requestdto.CommentRequestDto;
import com.sparta.securityprac.security2.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String commentWriter;

    @Column(nullable = false)
    private String comment;

    public Comment(CommentRequestDto requestDto, UserDetailsImpl userDetails){
        this.commentWriter = userDetails.getUsername();
        this.comment = requestDto.getComment();
    }
}
