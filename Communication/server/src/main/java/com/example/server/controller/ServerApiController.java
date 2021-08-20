package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/server")
@Slf4j
public class ServerApiController {
//https://openapi.naver.com/v1/search/local.json
// ?query=%EC%A3%BC%EC%8B%9D
// &display=10
// &start=1
// &sort=random
    @GetMapping("/naver")
    public String naver(){
        //네이버 지역 검색

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query","한식집") //검색할 내용
                .queryParam("display",10)
                .queryParam("start",1)
                .queryParam("sort","random")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        log.info("uri:{}",uri);


        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id","37USLSUDvaJG4Rs_cySl")
                .header("X-Naver-Client-Secret","96nMdbdX6X")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        return result.getBody();
    }

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
