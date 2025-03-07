package com.ganesh.AOP.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LoggingAspectV2 {

    @Before("allServiceMethodPointCut()")
    public void beforeServicePointCut(JoinPoint joinPoint){
        log.info("before service point cut, {}",joinPoint.getSignature());
    }


//    @After("allServiceMethodPointCut()")
    @AfterReturning(value="allServiceMethodPointCut()",returning="returnObj")
    public void afterServicePointCut(JoinPoint joinPoint,Object returnObj){
        log.info("after returning service point cut, {}",joinPoint.getSignature());
        log.info("method returning value,{}",returnObj);
    }

    @AfterThrowing("allServiceMethodPointCut()")
    public void afterServiceThrowPointCut(JoinPoint joinPoint){
        log.info("after throwing service point cut,{}",joinPoint.getSignature());
    }

    @Around("allServiceMethodPointCut()")
    public Object executionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime=System.currentTimeMillis();
        Object returnedValue=proceedingJoinPoint.proceed();
        Long endTime=System.currentTimeMillis();
        Long diff=endTime-startTime;
        log.info("Time taken for {} is {}",proceedingJoinPoint.getSignature(),diff);
        return returnedValue;
    }


    @Pointcut("execution(* com.ganesh.AOP.services.impl.*.*(..))")
    public void allServiceMethodPointCut(){
    }

}
