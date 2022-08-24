package com.callor.book.service.auth;

import com.callor.book.model.UserRole;
import com.callor.book.model.UserVO;
import com.callor.book.persistence.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserDetailsServiceImplV1 implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailsServiceImplV1(UserDao userDao) {
        this.userDao = userDao;
    }
    /*
    * 현재 userDao.findById() method 를 실행하면
     * tbl_users table 과 tbl_authorites table 을 같이 select
     * @OnToMany 설정에 fetch = FetchType.LAZY 부분을 설정
     *
     * tbl_users 테이블을 먼저 select 하고 tbl_authorities table 을 select 하기까지
     * 작업 사이의 시간동안 다른 프로세스(JOB) 이 tbl_authoriteis table 을 변경하면 데이터 참조 무결성이 무너진다
     *
     * 이것을 방지하기 위하여 2개 이상의 table 에 CRUD 를 수행할때는
     * 반드시 Transaction 으로묶어야한다
     * failed to lazily initialize a collection of role
     * @Transactional
     *
    */

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*
        findById() 는 Optional type VO 를 return 한다
        Optional type 의 vo 에서 실제 UserVO 데이터를 get 하기 위하여
        orElse() 를 사용하는데
        orElse() 매개변수로 blank UserVO 생성
        username 으로 select 시 데이터가 없으면
        blank UserVO 를 얻게된다
        null check 없이도 안전한 코드 겟
         */
        // null 값 방지처리
        UserVO userVO = userDao.findById(username).orElse(UserVO.builder().build());
        if(!userVO.getUsername().equals(username)) {

            throw new UsernameNotFoundException(username + "없으");

        }
        log.debug("로그인한 사용자{}", userVO);

        List<GrantedAuthority> grantList = new ArrayList<>();
        Set<UserRole> roleList = userVO.getUserRoles();
        for(UserRole role: roleList) {
            log.debug("사용자 ROLE 정보 {}", role.getRolename());
            // 문자열로 되어있는 ROLE 정보를 GrantedAuthority 정보로 변환하여 grantList 에 추가
            grantList.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        userVO.setAuthorities(grantList);
        return userVO;
    }
}
