package com.callor.data.persistance;

import com.callor.data.model.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring-data(JPA)에서는 Genericdao 대신 JpaRepository 를 상속받아 사용한다
/*
* 자동 import 하기
* 빨간색으로 나타나는 클래스, method 에 커서를 두고 ALT+ENTER
* import 최적화 : Ctrl + ALT + O
* */
public interface UserRepository extends JpaRepository<UserVO, Long> {
    // Ctrl + O

}
