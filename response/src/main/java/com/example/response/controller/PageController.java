package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //String 리턴 시 리소스를 찾게 됨

public class PageController {
    //html리턴
    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    //Json 리턴
    //1. ResponseEntity 사용
    //2. ResponseBody 사용
    @ResponseBody //리소스 찾아서 리턴 x. body만들어서 라턴해줌
    @GetMapping("/user")
    public User user(){
        var user = new User(); //java11부터 사용 User user = new user과 같음
        user.setName("steven");
        user.setAddress("서울시");
        return user;
    }

}

