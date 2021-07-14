package com.example.hello.controller;
//실제로 Get만 나누지x 실습을 위해 따로 나눔

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {
    @GetMapping(path="/hello") // http://localhost:8989/api/get/hello
    public String getHello(){
        return "getHello";
    }

   // @RequestMapping("/hi") //get/post/put / delete 등 모든 메서드가 매핑됨.
   @RequestMapping(path="/hi", method= RequestMethod.GET) // //http://localhost:8989/api/get/hi
    public String hi(){
        return "hi";
    }

    //http://localhost:8989/api/get/path/path-variable/{name}
    //변화하는 값을 받음. 변하는 값이 들어올 때마다 주소를 바꿔주는 건 어려움. //{}을 사용해서 변하는 부분을 지정해줌
    @GetMapping("/path-variable/{name}") //name값에 입력되는 대로 아래 출력값도 정해짐
    //public String pathVariable(@PathVariable String name){ // System.out.println("PathVariable :"+name);
    public String pathVariable (@PathVariable(name="name") String pathName){
        System.out.println("PathVariable :"+pathName);
        return pathName;
    }
//web 쿼리 파라미터
// search?q = intellij
// &oq = %E3%85%91%E3%85%9C%E3%85%85
// &aqs = chrome.3.69i57j0i433l9.2566j0j7
// &sourceid = chrome
// &ie = UTF-8
// ?key=value&key2=value2

    // http://localhost:8989/api/get/query-param?user=steve&email=steve@gmail.com&age=30
    @GetMapping(path= "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){

        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach(entry -> { ///람다식
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println();

            sb.append(entry.getKey()+"="+entry.getValue()+"\n");

        });
        return sb.toString();
    }

    //받을 값들을 지정해주는 경우
    @GetMapping("query-param02")
    public String queryPatam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ){
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name+" "+email+" "+age;
    }

    //가장 많이 사용하는 방법
   // dto/userRequest.java 사용

    //?user=steve&email=steve@gmail.com&age=30를 스프링 부트에서 판단 후 키에 해당하는 값을 DTO의 변수와 이름 매칭을 함
    //지정하지 않은 city=서울 등은 매핑하지 않음. 때문에 누락값 없는 지 확인해야함
    @GetMapping("query-param03")
    public String queryPatam03(UserRequest userRequest){ //@RequestParam를 붙이지 않음.
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }
}
