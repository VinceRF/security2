package com.sparta.securityprac.security2.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor //기본 생성자를 생성합니다
@Entity // DB 테이블 역할을 합니다
public class User {

    //ID 자동으로 생성 및 증가
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userid;

    //nullable: null 허용 여부
    //unique: 중복 허용 여부 (false일 때 중복허용)
    @Column(nullable = false,unique = true)
    private String username;

    @Column
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;


    public User(String username, String password, UserRoleEnum role){
        this.username = username;
        this.password = password;
        this.role = role;
    }


}
