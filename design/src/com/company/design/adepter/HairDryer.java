package com.company.design.adepter;

public class HairDryer implements Electronic110V{

    @Override
    public void powerOn() {
        System.out.println("헤어드라이기  110v on");

    }
}
