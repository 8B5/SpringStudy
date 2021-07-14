package com.example.ioc;
import java.util.Base64;
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
    public Encoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }
    //Base64Encoder과 UrlEncoder는 IEncoder를 상속받음.  IEncoder자리에 그 둘이 와도 괜찮음.
    //main에서 Base64Encoder과 UrlEncoder를 보내서 사용

    public String encode(String message){
        return iEncoder.encode(message);
    }
}
