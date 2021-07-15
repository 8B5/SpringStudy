package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))") //어디에 적용 시킬건지 명시
    //..*.*(..)앞의 하위에 있는 모든 메소드에 적용시키겠다. 수식이 굉장히 많음, 한번 찾아볼것
    private void cut(){}

    @Before("cut()") //지점을 적어줌 //하위의 메소드가 실행 되기 전에 실행될 것.
    public void before(JoinPoint joinPoint){ //들어가는 지점
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs();
        for(Object obj : args){
            System.out.println("method : "+obj.getClass().getSimpleName());
            System.out.println("value : "+obj);
        }
    }
    @AfterReturning(value = "cut()", returning = "returnObj") //메소드가 실행되고 리턴될 때
    public void afterReturn(JoinPoint joinPoint, Object returnObj){ //returning에 지정된 값과 매칭해줘야함
        System.out.println("returnObj");
        System.out.println(returnObj);
    }

}
