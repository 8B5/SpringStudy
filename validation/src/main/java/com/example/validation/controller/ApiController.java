package com.example.validation.controller;

import com.example.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {
    @PostMapping("/user")
    //Validation 사용 시 @Valid붙여줘야함
    //BindingResult해주면 Valid값의 결과가 BindingResult로 들어오게 됨.
    public Object user(@Valid @RequestBody User user, BindingResult bindingResult){
        //bindingResult에 에러 들어오면 처리
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder(); //https://hardlearner.tistory.com/288 StringBuilder에 대한 설명
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError)objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field: "+field.getField());
                System.out.println(message);

                sb.append("field: "+field.getField());
                sb.append("message:"+message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());

        }

        //logic
        // System.out.println(user);
        return user;
    }

    /* 고전적으로 90살 이상은 제한하는 방법. 페이지에는 400 error이 뜸.
    public ResponseEntity user(@RequestBody User user) {
        System.out.println(user);
        if(user.getAge() >= 90){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }
        return ResponseEntity.ok(user);
    }
     */
}
