package com.example.likelion4teamtestproject.UserInfo.Service;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class UserServiceAdvice {

    @Before(value = "s(jsonObject)")
    public void getUserInfoOfJsonObject(JSONObject jsonObject) throws Throwable {
        log.info(jsonObject.toString());
    }

    @Pointcut(value = "execution(* *..*ServiceImpl.createUserInfo()) && args(jsonObject)")
    public void s(JSONObject jsonObject){}

}
