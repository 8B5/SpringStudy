package com.company.design.singleton;

public class AClazz { //class가 예약어이기 때문에 Clazz라고 많이 사용함.
    private SocketClient socketClient;

    public AClazz(){
        this.socketClient = SocketClient.getInstance();
       // this.socketClient = new SocketClient(); //기본생성자를 private로 막지 않았을 때 (public)
    }

    public SocketClient getSocketClient(){
        return this.socketClient;
    }
}
