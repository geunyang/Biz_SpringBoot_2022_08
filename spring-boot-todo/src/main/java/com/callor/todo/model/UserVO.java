package com.callor.todo.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "tbl_todousers", schema = "bootDB")
public class UserVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long u_seq;
    @Column(nullable = false, unique = true)
    private String u_nickname;
    @Column(nullable = false)
    private String u_password;
    @Column(length = 125, nullable = false)
    private String email;
}
