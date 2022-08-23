package com.callor.todo.service;

import com.callor.todo.model.UserVO;

import java.util.List;

public interface UserService {

    public List<UserVO> selectAll();
    public UserVO findById(Long seq);
    public void insert(UserVO userVO);
    public void update(UserVO userVO);
    public void delete(String seq);
}
