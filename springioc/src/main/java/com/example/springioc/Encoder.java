package com.example.springioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component

public class Encoder{
    private IEncoder iEncoder;
    /*
    public Encoder(){
        //this.iEncoder = new Base64Encoder();
        this.iEncoder = new UrlEncoder();
    } */
    //인코딩 변환해야할 때 마다 여기로 와서 처리해줌
    // =>비효율적이고 본질의 클래스를 건들게 됨.

    //DI 추가
    //@Component된 것이 많을 땐 spring에서 어떤 걸 매칭해줘야할지 명시해줘야함.
    //@Qualifier("bean의 이름")
    public Encoder(@Qualifier("base64") IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }
    //Base64Encoder과 UrlEncoder는 IEncoder를 상속받음.  IEncoder자리에 그 둘이 와도 괜찮음.
    //main에서 Base64Encoder과 UrlEncoder를 보내서 사용

    //set method 생성
    //bean을 주입받을 수 있는 곳이 변수, 생성자, set method
    public void setIEncoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }
}
