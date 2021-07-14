package com.company.design.singleton;

public class SocketClient {
    private static SocketClient socketClient = null; //singleton은 자기 자신을 객체로 가지고 있어야함.
    private SocketClient(){//기본생성자를 private로 선언(막아야)해야함.
    }
    //public SocketClient(){} //기본생성자를 private로 막지 않았을 때 (public)
    public static SocketClient getInstance(){

        if(socketClient ==null){ //호출 시 없는 경우에는 생성, 있는 경우
            socketClient = new SocketClient();
        }
        return socketClient;
    }

    public void connect(){
        System.out.println("connect");
    }
}
