package com.callor.book.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "tbl_authorities")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;


    private String username;
    private String rolename;
    
    /*
    두 테이블을 1대 다 관계로 설정할 경우
    Master 클래스에 @ManyToMany 를 설정하고
    관계 연결되는 클래스에는 @ManyToOne 설정이 필요
    
    name : UserRole.username 칼럼과
    referencedColumnName : UserVO.username 칼럼을 서로 연결
    
    insertable = false, updatable = false 속성을 false 로 선언
    tbl_users, tbl_authorities 테이블에 insert 나 update 가 발생한 경우 동시 실행 막아줌
    읽기 전용으로만 사용하겠다는 의미
     */
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private UserVO userVO;
}
