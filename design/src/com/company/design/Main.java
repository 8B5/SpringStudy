package com.company.design;

import com.company.design.adepter.*;
import com.company.design.aop.AopBrowser;
import com.company.design.proxy.Browser;
import com.company.design.proxy.BrowserProxy;
import com.company.design.proxy.IBrowser;
import com.company.design.singleton.AClazz;
import com.company.design.singleton.BClazz;
import com.company.design.singleton.SocketClient;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) {
/*singleton
        AClazz aClazz = new AClazz();
        BClazz bClazz = new BClazz();

        SocketClient aClient = aClazz.getSocketClient();
        SocketClient bClient = bClazz.getSocketClient();

        System.out.println("두개의 객체가 동일한가");
        System.out.println(aClient.equals(bClient)); //true
        //기본생성자를 private로 막지 않았을 때 (public) // false
*/
        //proxy
        /*
        Browser browser = new Browser("www.naver.com");
        browser.show(); //html 파일 하나 받아옴. // browser loading html from: www.naver.com
        browser.show();
        browser.show();
        browser.show();

        IBrowser browser = new BrowserProxy("www.naver.com");
        browser.show(); //BrowserProxy loading html from: www.naver.com
        browser.show(); //BrowserProxy use cache html: www.naver.com
        browser.show(); //BrowserProxy use cache html: www.naver.com
        browser.show(); //BrowserProxy use cache html: www.naver.com
        browser.show(); //BrowserProxy use cache html: www.naver.com
        //처음에만 loading 하고, 다음부터는 가지고 있던 html을 가져옴
*/
        /*
        AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();

        IBrowser aopBrowser = new AopBrowser("www.naver.com",
                //lamda식
                ()->{
                    System.out.println("before");
                    start.set(System.currentTimeMillis());
                },
                ()->{
                    long now  = System.currentTimeMillis();
                    end.set(now-start.get());
                }
        );
        aopBrowser.show();//AopBrowser html cache: www.naver.com
        System.out.println("loading time: "+end.get()); //loading time: 1537

        aopBrowser.show();//AopBrowser html cache: www.naver.com
        System.out.println("loading time: "+end.get());//loading time: 0 //loading시간 안걸림
*/
        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        Cleaner cleaner = new Cleaner();
        Electronic110V adapter = new SocketAdapter(cleaner);
        connect(adapter); //adapter를 통해서 cleaner실행
        //청소기 220v on

        AirConditioner airConditioner = new AirConditioner();
        Electronic110V airAdapter = new SocketAdapter(airConditioner);
        connect(airAdapter); //에어컨 220v on
    }
    //콘센트
    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn(); //헤어드라이기  110v on


    }
}
