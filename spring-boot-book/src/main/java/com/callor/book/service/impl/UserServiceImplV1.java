package com.callor.book.service.impl;

import com.callor.book.model.UserRole;
import com.callor.book.model.UserVO;
import com.callor.book.persistence.UserDao;
import com.callor.book.persistence.UserRoleDao;
import com.callor.book.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplV1 implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final UserRoleDao userRoleDao;

    public UserServiceImplV1(PasswordEncoder passwordEncoder, UserDao userDao, UserRoleDao userRoleDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.userRoleDao = userRoleDao;
    }

    // 회원가입을 하기 위한 Service Method
    /*
    회원가입시 최초의 회원이면 ROLE_ADMIN, ROLE_USER 를 설정
    enabled 등을 true 로 설정
    
    두번째 이후 회원가입 하는 경우 ROLE_USER 만 지정하고
    enabled 등은 기본값(false) 로 설정
     */
    @Transactional
    @Override
    public UserVO join(UserVO userVO) {

        // tbl_users table 의 데이터 개수 확인
        Long userCount = userDao.count();
        List<UserRole> roleList = new ArrayList<>();
        
        /*테이블에 데이터가 없으면 count() 연산은 0을 보여준다
        * userCount == 0 의 논리식도 문제가 없어 보이나
        * 일부 DBMS 엔진중에ㅐ table 에 데이터가 없거나 문제가 발생하면 
        * 음수(-) 값을 보여주는 경우가 있어
        * userCount == 0 과 같은 논리식을 사용한다
        * */
        if(userCount < 1) {
            // Legacy 에서는 선택적으로 사용했으나 boot 에서는 필수적으로 체크하는 항목이므로 설정 필수
            userVO.setEnabled(true);
            userVO.setAccountNonExpired(true);
            userVO.setAccountNonLocked(true);
            userVO.setCredentialsNonExpired(true);

            roleList.add(UserRole
                    .builder()
                    .username(userVO.getUsername())
                    .rolename("ROLE_ADMIN")
                    .build());
            roleList.add(UserRole
                    .builder()
                    .username(userVO.getUsername())
                    .rolename("ROLE_USER")
                    .build());
        } else {

            userVO.setEnabled(false);
            userVO.setAccountNonExpired(true);
            userVO.setAccountNonLocked(true);
            userVO.setCredentialsNonExpired(true);

            roleList.add(UserRole
                    .builder()
                    .username(userVO.getUsername())
                    .rolename("ROLE_USER")
                    .build());

        } //end if

        String password = userVO.getPassword();
        String encPassword = passwordEncoder.encode(password);
        userVO.setPassword(encPassword);

        // 1개의 데이터를 insert or update
        userDao.save(userVO);

        // List 에 담긴 데이터를 insert all or update all
        userRoleDao.saveAll(roleList);
        
        return null;
    }
}
