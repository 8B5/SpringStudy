package com.example.springioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {
        SpringApplication.run(IocApplication.class, args);

        ApplicationContext context = ApplicationContextProvider.getContext();

     //   Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
     //   UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

     //   Encoder encoder = new Encoder(base64Encoder);
     //   Encoder encoder = context.getBean(Encoder.class);

     //   Encoder encoder = context.getBean("base64Encode",Encoder.class); //지정된 bean사용
        Encoder encoder = context.getBean("urlEncode",Encoder.class); //지정된 bean사용

        String url = "www.naver.com/books/it=?page=10&size=20&name=spring-boot";//인코딩할 url

        String result = encoder.encode(url);
        System.out.println(result); //base64

       // encoder.setIEncoder(urlEncoder);
       // result = encoder.encode(url);
       // System.out.println(result);//utf8


    }

}

//bean직접 지정
@Configuration //스프링 컨테이너 . 스프링에서 객체를 직접 관리하는 것을 Bean이라고 부름.
                //그러한 Bean들이 관리되는 곳이  Spring컨테이너. 컨테이너가 제어할 권한을 가져갔음 (제어의 역전)
                // =>Ioc
class AppConfig{
    @Bean("base64Encode")
    public Encoder encoder(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }

    @Bean("urlEncode")
    public Encoder encoder(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }
    //encoder라는 bean이 2개가 생김.
    //구분을 위해서는 명칭을 붙여줌
}