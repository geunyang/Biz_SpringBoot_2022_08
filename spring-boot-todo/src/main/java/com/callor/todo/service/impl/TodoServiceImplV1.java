package com.callor.todo.service.impl;

import com.callor.todo.model.TodoVO;
import com.callor.todo.persistance.TodoDao;
import com.callor.todo.service.TodoService;

import java.util.List;
import java.util.Optional;

public class TodoServiceImplV1 implements TodoService {

    private final TodoDao todoDao;

    public TodoServiceImplV1(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    public List<TodoVO> selectAll() {
        return todoDao.findAll();
    }

    @Override
    public TodoVO findById(Long seq) {
        Optional<TodoVO> opTodoVO = todoDao.findById(seq);
        TodoVO todoVO = opTodoVO.orElse(new TodoVO());
        return todoVO;
    }

    @Override
    public int insert(TodoVO todoVO) {
        TodoVO result = todoDao.save(todoVO);
        return 0;
    }

    @Override
    public int update(TodoVO todoVO) {
        todoDao.save(todoVO);
        return 0;
    }

    @Override
    public int delete(TodoVO todoVO) {
        return 0;
    }
}
