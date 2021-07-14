package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("--------");

        //Object Mapper
        //Text JSON -> Object
        //Object -> Text JSON

        //controller req json(text) - > object
        // response object -> json(text)

        var objectMapper = new ObjectMapper();

        //object -> text
        //object mapper가 get method를 참조한다.(User에 getter)
        // get method외의 method명에 get을 넣어주면 에러가 생김. 주의!
        var user = new User("steve", 10, "010-2222-1111");
        //변환
        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);
        
        
        //text - > object
        //object mapper는 default 생성자를 필요로 한다.
        var objectUser = objectMapper.readValue(text, User.class); //변경될 타입
        System.out.println(objectUser);

    }

}
