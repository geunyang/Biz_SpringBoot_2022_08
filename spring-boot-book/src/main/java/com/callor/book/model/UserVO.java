package com.callor.book.model;
/*
* JPA 프로젝트에서 자주 발생하는 오류메시지
* 근본적인 원인은 
* @OneToMany, @ManyToOne 을 설정하면
* tbl_users table 을 select 할때 tbl_authorities table 을 join 하여 select 한 후 하나의 VO 객체에 담긴다
* 이 코드를 사용할 때 lombok 의 @ToString 을 사용하면 
* ToString() method 생성시 문제를 일으킨다
* 이때문에 @ToString 를 사용하지 않고 직접 만들어야한다*/
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
//@OneToMany 와 충돌하여 사용하지 않는다
@Builder
@Entity
@Table(name = "tbl_users")
public class UserVO implements UserDetails {

    @Id
    private String username;

    private String password;
    private boolean enabled;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    private String email;
    private String realname;
    
    /*
    이 칼럼은 table 생성시 만들지않고 VO에만 쓰겠다
     */
    @Transient
    Collection<? extends GrantedAuthority> authorities;

    /*
    @OneToMany : tbl_authorities table 과 1대 다 관계를 선언하기
    이 선언을 하게되면 자동으로 tbl_users 테이블을 select 할때 
    tbl_authorities table 도 같이 select 하여 하나의 묶음으로 만든다

    UserVO(tbl_users) 와 UserRole(tbl_authorities) table 간에
    FK 설정이 자동으로 형성

    CascadeType.REMOVE : tbl_users 테이블에서 데이터를 delete 하면
    tbl_authorities 테이블에 연관된 데이터도 같이 삭제하라는 명령

    tbl_users table 을 select 하고 시간을 지연시킨 후
    tbl_authorities table 을 select 하라

    FetchType.LAZY : ????
    
    List<UserRoles> type 으로 지정할 수 있으나
    Set<UserRoles> type 으로 지정한 이유
    성질이 비슷하나 Set 이 List 에 비해서 검색 속도가 빠르다.
    Set 은 중복된 데이터를 허용하지 않으며 중복 데이터 add 시 원래있던 데이터를 update 한다
    Set<String> strSet = new HashSet<String>
    strSet.add("홍길동");
    strSet.add("이몽룡");
    strSet.add("홍길동");
    실제 strSet 에는 2개의 데이터만 존재
    
    Set 을 생성할때 HashSet 을 사용하면 데이터가 add 한 순서를 무시하고 무작위로 저장된다
    add 한 데이터의 순서를 보장하려면
    Set<String> strSet = new LinkedHashSet<String>() 를 사용하여 객체를 초기화(Java 1.4 부터 지원)
    
     */
    @OneToMany(mappedBy = "userVO",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles;

    @Override
    public String toString() {
        return "UserVO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", email='" + email + '\'' +
                ", realname='" + realname + '\'' +
                '}';
    }
}
