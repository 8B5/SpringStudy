package com.example.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //(basePackages = "com.example.exception") 등을 붙여서 해당되는 패키지 하위 예외를 다 컨드롤 해주게 할 수 있음
public class GlobalControllAdvice {
    // @ExceptionHandler(value = 내가 잡고싶은 예외)
    @ExceptionHandler(value = Exception.class)  //전체 예외 다 잡음
    public ResponseEntity exception(Exception e){
        System.out.println(e.getClass().getName()); //org.springframework.web.bind.MethodArgumentNotValidException
        System.out.println("------------------");
        System.out.println(e.getLocalizedMessage());
        System.out.println("------------------");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    /*
    //아래 코드를 ApiController.java로 옮김 : 해당 컨트롤 내에서 일어나는 일만 관여를 하게 됨.
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }
    */
}
