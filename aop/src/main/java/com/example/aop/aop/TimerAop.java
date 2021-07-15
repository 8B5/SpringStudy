package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component //Spring에서 관리, 하나의 class에서 여러가지 Bean이 등록이 됨
//@Bean은 class에 붙일 수 x, method에서 사용.

//특정 부분의 걸리는 시간
public class TimerAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))") //어디에 적용 시킬건지 명시
    //..*.*(..)앞의 하위에 있는 모든 메소드에 적용시키겠다. 수식이 굉장히 많음, 한번 찾아볼것
    private void cut(){}

    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private void enableTimer(){}

    //@before와 @after은 타임을 공유할 수 없음
    
    @Around("cut() && enableTimer()") //두가지 조건을 같이 검
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();

        System.out.println("total time :" + stopWatch.getTotalTimeSeconds());
        //걸린 초 단위 시간 구할 수 있음.

    }


}
