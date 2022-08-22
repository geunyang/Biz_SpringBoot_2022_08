package com.callor.book.controller;

import com.callor.book.model.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }


}
