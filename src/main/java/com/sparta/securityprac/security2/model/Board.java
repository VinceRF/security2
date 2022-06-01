package com.sparta.securityprac.security2.model;

import com.sparta.securityprac.security2.requestdto.BoardRequestDto;
import com.sparta.securityprac.security2.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Board extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long boardId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    public Board ( String title, String content){
        this.title = title;
        this.content = content;
    }


    public Board(BoardRequestDto requestDto, UserDetailsImpl userDetails) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.writer = userDetails.getUsername();
    }

    public void update(BoardRequestDto requestDto, UserDetailsImpl userDetails){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.writer = userDetails.getUsername();
    }


}
