package com.company.design.proxy;

public class Browser implements IBrowser {

    private String url;

    public Browser(String url){
        this.url = url;
    }

    @Override
    public Html show() {
        System.out.println(" browser loading html from: "+url ); //soutv : 값 있는 sysout 프린트문 생성
        return new Html(url);
    }
}
