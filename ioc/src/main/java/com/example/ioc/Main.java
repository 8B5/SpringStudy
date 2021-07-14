package com.example.ioc;

public class Main {

    public static void main(String[] args){
        String url = "www.naver.com/books/it=?page=10&size=20&name=spring-boot";//인코딩할 url

        //Base 64 encoding

        //url encoding

        IEncoder encoder = new Base64Encoder();
        String result = encoder.encode(url);
        System.out.println(result); //d3d3Lm5hdmVyLmNvbS9ib29rcy9pdD0/cGFnZT0xMCZzaXplPTIwJm5hbWU9c3ByaW5nLWJvb3Q=

        IEncoder urlEncoder = new UrlEncoder(); //utf8
        String urlResult = urlEncoder.encode(url);
        System.out.println(urlResult); //www.naver.com%2Fbooks%2Fit%3D%3Fpage%3D10%26size%3D20%26name%3Dspring-boot

        //urlEncoder와 Encoder은 같은 역할을 하고 있음. => 인터페이스를 뽑을 수 있음.

        /*
        Encoder encoder2 = new Encoder();
        String result2 = encoder2.encode(url);
        System.out.println(result2);
         */
        //인코딩 변환해야할 때 마다 Encoder.java의 Encoder()가서 처리해줌
        //=>비효율적이고 본질의 클래스를 건들게 됨.

        //DI
        Encoder encoder2 = new Encoder(new Base64Encoder()); //new UrlEncoder()
        String result2 = encoder2.encode(url);
        System.out.println(result2);
        //클래스를 건들지 않고 new 부분을 바꿔주면 인코딩 변환 가능.
        //Base64Encoder과 UrlEncoder는 IEncoder를 상속받음.  IEncoder자리에 그 둘이 와도 괜찮음.
        //main에서 그 자식 둘을 선택해서 사용함

    }
}
