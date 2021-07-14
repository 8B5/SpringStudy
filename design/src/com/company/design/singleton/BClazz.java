package com.company.design.singleton;

public class BClazz {
    private SocketClient socketClient;

    public BClazz(){
         this.socketClient = SocketClient.getInstance();
        // this.socketClient = new SocketClient(); //기본생성자를 private로 막지 않았을 때 (public)
    }

    public SocketClient getSocketClient(){
        return this.socketClient;
    }
}
