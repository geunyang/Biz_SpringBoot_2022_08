package com.callor.data.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
// VO 클래스를 JPA의 Data Table 화 하기 위한 선언
@Entity
// 지금부터 이 클래스는 Entity 설계를 위한 도구라는 선언
@Table(name="tbl_users",schema="bootDB")
// bootDB 라는 데이터베이스에 tbl_users 라는 table 을 UserVO 를 참조하여 가정, 혹은 강제 생성
public class UserVO {

    /*seq bigint primary key auto_increment 설정하기*/
    // tbl_users의 primary key 선언이 반드시 필요
    @Id // seq 칼럼이 PK 이다 라는 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Mysql Auto_increment 설정을 하여 seq 값을 관리하라
    private long seq;

    /* username varchar(20) not null unique */
    // ????
    @Column(columnDefinition = "VARCHAR(20)",nullable = false, unique = true)
    private String username;

    /* password varchar(255) not null */
    // DB 종류에 관계없이 문자열 255자로 설정
    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 125)
    private String email;

    @Column(length = 20)
    private String phone;

    // @Column 을 지정하지 않으면
    // String 형의 경우 문자열(varchar) 255 를 default 로 설정
    private String address;

    @Column(length = 20)
    private String realname;

    @Column(length = 20)
    private String nickname;

}
