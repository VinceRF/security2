package com.sparta.securityprac.security2.service;

import com.sparta.securityprac.security2.model.User;
import com.sparta.securityprac.security2.model.UserRoleEnum;
import com.sparta.securityprac.security2.repository.UserRepository;
import com.sparta.securityprac.security2.requestdto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor // 기본 생성자를 생성 이 어노테이션으로 아래에 주석이 달린 생성자를 생략 가능하다.
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

//    @Autowired
//    public UserService(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }

    //회원 중복 기능
    public void registerUser(SignupRequestDto requestDto){
        String username = requestDto.getUsername();
//        String password = requestDto.getPassword();
        //회원 ID 중복 검사
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()){
            throw new IllegalArgumentException("중복된 ID 입니다.");
        }

        String password = passwordEncoder.encode(requestDto.getPassword());
        UserRoleEnum role = UserRoleEnum.USER;


        User user = new User(username,password,role);
        userRepository.save(user);
        System.out.println(user);

    }





}
