package com.example.client.service;

import com.example.client.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {
    // http://localhost/api/server/hello를 받아올 것
    //response
    public UserResponse hello(){ //UserResponse로 받을 것. controller도 수정
       URI uri =  UriComponentsBuilder.fromUriString("http://localhost:8989")
                    .path("/api/server/hello")
                    .queryParam("name","aaa")//?name=steve
                    .queryParam("age", 99)//&age=10
                    .encode()
                    .build().toUri();
       //http://localhost:8989/api/server/hello?name=steve&age=10
        System.out.println(uri.toString());
        RestTemplate restTemplate = new RestTemplate();
        //String result = restTemplate.getForObject(uri, String.class); //받을 타입 지정
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
        //ResponseEntity를 사용하면 getStatusCode,getBody 사용 가능
        System.out.println(result.getStatusCode()); //200 OK
        System.out.println(result.getBody()); //내용 //hello server
        return result.getBody();
    }
}
