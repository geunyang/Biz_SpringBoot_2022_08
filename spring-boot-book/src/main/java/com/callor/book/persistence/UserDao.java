package com.callor.book.persistence;

import com.callor.book.model.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserVO, String> {


}
