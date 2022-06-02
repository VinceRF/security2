package com.sparta.securityprac.security2.model;

import com.sparta.securityprac.security2.requestdto.CommentRequestDto;
import com.sparta.securityprac.security2.security.UserDetailsImpl;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Timestamped {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    private String commentWriter;

    @Builder
    public Comment(String content, Board board, User user){
        this.user = user;
        this.content = content;
        this.board = board;
        this.commentWriter = user.getUsername();
    }

    public void editComment(String content) {
        this.content = content;
    }
}
