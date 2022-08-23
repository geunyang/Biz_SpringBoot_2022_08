package com.callor.todo.controller;

import com.callor.todo.model.TodoVO;
import com.callor.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class HomeController {

    private final TodoService todoService;

    public HomeController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping(value = {"","/"})
    public String home(@RequestMapping(name = "seq", required = false, defaltValue = "") Long seq, Model model) {

        List<TodoVO> todoList = todoService.selectAll();
        TodoVO todoVO = null;

        return "home";
    }
}
