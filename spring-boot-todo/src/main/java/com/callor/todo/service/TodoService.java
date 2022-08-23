package com.callor.todo.service;

import com.callor.todo.model.TodoVO;

import java.util.List;

public interface TodoService {


    public List<TodoVO> selectAll();

    public TodoVO findById(Long seq);

    public int insert(TodoVO todoVO);
    public int update(TodoVO todoVO);
    public int delete(TodoVO todoVO);

}
