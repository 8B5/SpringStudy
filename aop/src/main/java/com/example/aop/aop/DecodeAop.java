package com.example.aop.aop;

import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))") //어디에 적용 시킬건지 명시
    //..*.*(..)앞의 하위에 있는 모든 메소드에 적용시키겠다. 수식이 굉장히 많음, 한번 찾아볼것
    private void cut(){}

    @Pointcut("@annotation(com.example.aop.annotation.Decode)")
    private void enableDecode(){}

    @Before("cut() && enableDecode()")
    public void befor(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();
        for(Object arg : args){
            if(arg instanceof User){ //user와 매칭 시
                User user = User.class.cast(arg);
                
                String base64Email = user.getEmail();
                String email = new String(Base64.getDecoder().decode(base64Email),"UTF-8") ;
                //byte타입이기 때문에 string으로 바꿔줌

                user.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        //반대로 인코딩
        if(returnObj instanceof User){
            User user = User.class.cast(returnObj);

            String email = user.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes());

            user.setEmail(base64Email);
        }

    }

}
