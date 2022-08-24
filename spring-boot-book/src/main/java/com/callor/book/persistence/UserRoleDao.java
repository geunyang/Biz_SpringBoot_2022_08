package com.callor.book.persistence;

import com.callor.book.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

// 클래스타입으로 지정!
public interface UserRoleDao extends JpaRepository<UserRole, Long> {
}
