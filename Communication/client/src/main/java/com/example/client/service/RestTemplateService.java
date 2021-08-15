package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.apache.catalina.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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
        //getForEntity : http의 get메소드를 Entity로 가져오겠다.
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
        //ResponseEntity를 사용하면 getStatusCode,getBody 사용 가능

        System.out.println(result.getStatusCode()); //200 OK
        System.out.println(result.getBody()); //내용 //hello server

        return result.getBody();
    }
//server가 어떤 식으로 데이터를 주는지 모를 때는 void로
    //public UserResponse post(){
    public void post(){
        // http:// localhost: /api/server/user/{userID}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8989")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve") //{}의 값들 순서대로 작성해줌
                .toUri();
        System.out.println(uri);

        //http body -> object - > object mapper -> json -> rest template -> http body json
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        //응답받을 형식
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, req, UserResponse.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
        //String형식으로 찍어줌. 형태 모를 때 사용가능(안맞아서 안찍히는 부분 알아낼 수 있음)
        
       // return response.getBody();
    }
    public UserResponse exchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8989")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve") //{}의 값들 순서대로 작성해줌
                .toUri();
        System.out.println(uri);

        //http body -> object - > object mapper -> json -> rest template -> http body json
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abcd")
                .header("custom-header", "ffffff")
                .body(req);

        //해더 실어서 보내기기
       RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity,UserResponse.class);
        return response.getBody();

    }

    public Req<UserResponse> genericExchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8989")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve") //{}의 값들 순서대로 작성해줌
                .toUri();
        System.out.println(uri);

        //http body -> object - > object mapper -> json -> rest template -> http body json

        UserRequest userRequest = new UserRequest();
        userRequest.setName("steve");
        userRequest.setAge(10);

        Req<UserRequest> req = new Req<UserRequest>();
        req.setHeader(
            new Req.Header()

        );
        req.setResBody(
            userRequest

        );



        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abcd")
                .header("custom-header", "ffffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();


        ResponseEntity<Req<UserResponse>> response =
                restTemplate.exchange(requestEntity,  new ParameterizedTypeReference<Req<UserResponse>>(){});
        //new ParameterizedTypeReference<Req<UserResponse>>(){}; //Req<UserResponse>.class사용하고 싶을 때.
        //제네릭 타입을 이미 선언해주었기 때문에 생략 가능

        return response.getBody();
        //첫번째 getBody()는 ResponseEntity의 getBody
        //두번째 getBody()는 Req에 지정한 resBody

    }

}
