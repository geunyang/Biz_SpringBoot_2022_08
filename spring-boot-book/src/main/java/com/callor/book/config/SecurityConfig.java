package com.callor.book.config;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
@Configuration
Config 설정 클래스 선언
*-context.xml 파일 설정을 대신하는 클래스 선언

@Bean
이 Annotation 이 부착된 method 는 자동으로 실행되어 bean 들을 생성
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
    /*
    User 의 Request 가 Security 를 통하여 이루어지도록 하는 Bean
    */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/user/login").permitAll() // user/login 아무나
                .antMatchers("/user/join").permitAll() // user/join 아무나
                .antMatchers("/").permitAll() // root 접속 아무나
                
                // book/** 으로 시작되는 모든 요청은 login 필수
                .antMatchers("/book/**").authenticated()
                
                // 위에서 지정하지 않은 pattern 일 경우
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .and()
                .logout();
        return http.build();
    } // end SecurityFilterChain

    /*
    이 Bean 이 생성될때 3개의 객체 매개변수를 주입받는다
    이 주입받은 3개의 객체를 사용하여 Security 인증을 수행한다
    HttpSecurity httpSecurity,PasswordEncoder passwordEncoder,UserDetailsService userDetailsService
     */
    // 코드확인
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
                                                       PasswordEncoder passwordEncoder,
                                                       UserDetailsService userDetailsService) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();

    }
    
    /*
    /static, /js, /css, /upload, /files 와 같은 폴더는 
    controller 를 향하지 않고 바로 response 하도록 선언한다
    Legacy 에서 resources mapping 으로 설정하는 부분을 여기에서 선언
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        
        // 아래 pattern 으로 요청이 들어오면 security 를 거치지 않고 바로 통과시켜라
        return (web) -> web.ignoring()
                .antMatchers("/static/**", "/js/**", "/css/**", "/upload/**");
    }

}
