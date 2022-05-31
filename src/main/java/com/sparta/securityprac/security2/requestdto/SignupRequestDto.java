package com.sparta.securityprac.security2.requestdto;

import com.sparta.securityprac.security2.model.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String username;
    private String password;
}
