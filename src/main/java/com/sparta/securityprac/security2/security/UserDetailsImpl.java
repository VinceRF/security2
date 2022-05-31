package com.sparta.securityprac.security2.security;

import com.sparta.securityprac.security2.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
@Getter
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final User user;


    // 지정되지 않은 Collection 타입인 ? 객체가 GrantedAuthority 를 상속받았다.
    // 이 GrantedAuthority는 우리가 직접 설정? 해줘야한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
