package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class ApiController {
    @GetMapping("") //원래 ?name= 값이 없으면 실행이 안되는데 required을 지정해주면 실행이 됨
    public User get(@RequestParam(required = false) String name, @RequestParam Integer age){
        //@RequestParam(required = false)는 RequestParam가 없어도 동작을 하되, String name는 null이됨
        User user = new User();
        user.setName(name);
        user.setAge(age);
        
        int a = 10+age;

        return user;
    }
    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;

    }
    //이 컨트롤 내에서 일어나는 일만 관여를 하게 됨.
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        System.out.println("api controller");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }
}
