package com.example.springioc;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Base64;

@Component("base64") //명칭 지정
//SpringBean으로 등록됨 //제어의 역전
public class Base64Encoder implements com.example.springioc.IEncoder {
    public String encode(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
