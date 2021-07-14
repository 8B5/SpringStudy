package com.example.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //해당 class는 REST API 처리하는 Controller로 자동으로 인식 됨
@RequestMapping("/api") //RequestMapping은 URI를 지정해주는 Annotation. 주소 할당
public class ApiController {
    @GetMapping("/hello") //http://localhost:8989/api/hello 주소가 매핑이 됨.
    public String hello(){
        return "hello spring boot";
    }
}
