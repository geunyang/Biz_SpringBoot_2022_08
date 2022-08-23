package com.callor.todo.persistance;

import com.callor.todo.model.TodoVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoDao extends JpaRepository<TodoVO, Long> {
}
