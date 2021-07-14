package com.example.put;


import com.example.put.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {
    @PutMapping("/put/{userId}")
    public PostRequestDto put(@RequestBody PostRequestDto requestDto, @PathVariable(name = "userId")
            String id) {
        System.out.println(id);
        return requestDto;
        //@RestController인 경우에는 Object자체를 리턴시키면 spring에서 json 형태로 바꿔줌
    }
}