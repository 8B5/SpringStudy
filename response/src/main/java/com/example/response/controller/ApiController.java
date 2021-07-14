package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    //TEXT
    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }
    //JSON 가장 많이 사용
    //동작 방법 request -> object mapper -> object -> method - >object -> object mepper - >json ->response
    @PostMapping("/json") //PostMapping은 RequestBody 사용
    public User json(@RequestBody User user){
        return user;
    }
    
    //가장 명확하게 요청을 보내는 경우  //권장
    //http status(상태코드)를 정해줄 것. ResponseEntity를 사용
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(user); //201
    }

}
