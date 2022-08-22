package com.callor.book.config;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
Config 설정 클래스 선언
 */
@Slf4j
@Configuration
public class SecurityConfig {
    
    // 비밀번호 암호화 도구
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean method 프로젝트가 시작될때 자동으로 실행
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/").permitAll() // root 접속 아무나
                .antMatchers("/user/login").permitAll() // user/login 아무나
                .antMatchers("/user/join").permitAll() // user/join 아무나
                // book/** 으로 시작되는 모든 요청은 login 필수
                .antMatchers("/book/**").authenticated() 
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .and()
                .logout();
        return http.build();
    }

}
