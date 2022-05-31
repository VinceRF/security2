package com.sparta.securityprac.security2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePassword(){
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        // h2-console 허용 csrf,frame option 무시
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
        http.csrf()
                .ignoringAntMatchers("/user/**");


        http.authorizeRequests()

                // image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
                // css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
                // 회원가입 페이지 허용 GET방식만 허용
                .antMatchers("/user/**").permitAll()
                //어떤 요청이든 '인증'이 필요하다
                .anyRequest().authenticated()
                .and()
                    //로그인 기능 허용
                    .formLogin()
                    .loginPage("/user/login")
                    .loginProcessingUrl("/user/login") // POST 방식의 로그인 처리
                    .defaultSuccessUrl("/")
                    .failureUrl("/user/login?error")
                    .permitAll()
                .and()
                    //로그아웃 기능 허용
                    .logout()
                    .logoutUrl("/user/logout")
                    .permitAll();
    }
}

