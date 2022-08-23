package com.callor.todo.persistance;

import com.callor.todo.model.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserVO, Long> {
}
