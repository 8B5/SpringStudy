package com.example.ioc;

import com.example.ioc.IEncoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlEncoder implements IEncoder{

    public String encode(String message){
        try {
            return URLEncoder.encode(message, "UTF-8"); //message를 utf-8로 인코딩
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
