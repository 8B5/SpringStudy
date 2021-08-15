package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/server")
@Slf4j
public class ServerApiController {

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age){
        User user = new User();
        user.setName(name); //client쪽에서 지정한 값이 에코가 되어 돌아올 수 있도록
        user.setAge(age);

        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(
                     //HttpEntity<String> entity, //클라이언트가 보낸 값을 string으로 찍어낼 수 있음
                     @RequestBody Req<User> user,
                     @PathVariable int userId,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,
                     @RequestHeader("custom-header")String customHeader
    ){
        //log.info("req : {}", entity.getResBody());
        log.info("userId: {}, userName: {}",userId, userName);
        log.info("authorization : {}, custom :{}", authorization, customHeader); //header
        log.info("client req : {}", user);

        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header()
        );
        response.setResBody(
                user.getResBody() //에코
        );

        return response;
    }
}
