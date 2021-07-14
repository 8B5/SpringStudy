package com.example.delete.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {
    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable String userId, @RequestParam String account){
        System.out.println(userId);
        System.out.println(account);
        // delete - > 리소스 삭제. 삭제할 데이터가 있든 없든 200 메세지를 줌.
    }
}
