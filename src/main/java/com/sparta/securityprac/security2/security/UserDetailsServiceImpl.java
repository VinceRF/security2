package com.sparta.securityprac.security2.security;

import com.sparta.securityprac.security2.model.User;
import com.sparta.securityprac.security2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    // UserDetailService 를 컨트롤을 누른채로 클릭해보자

    private final UserRepository userRepository;

    //찾으면 호출하고 없으면 예외를 발생시켜라
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username + "을 찾을 수 없습니다."));
        return new UserDetailsImpl(user);
    }



}
