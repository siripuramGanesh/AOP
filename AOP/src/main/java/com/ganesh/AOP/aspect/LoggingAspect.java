package com.ganesh.AOP.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
@Component
@Slf4j
public class LoggingAspect {

//    @Before("execution(* orderPackage(..))")
//    @Before("execution(* com.ganesh.AOP.services.impl.*.orderPackage(..))")
    @Before("execution(* com.ganesh.AOP.services.impl.*.*(..))")
    public void beforeOrderPackage(JoinPoint joinPoint){
        log.info("Before called from LoggingAspect kind, {}",joinPoint.getKind());
        log.info("Before called from LoggingAspect signature, {}",joinPoint.getSignature());
    }

    @Before("within(com.ganesh.AOP.services.impl.*)")
    public void beforeServiceImplCalls(){
        log.info("Service Impl calls");
    }

    @Before("@annotation(java.beans.Transient)")
    public void beforeTransactionalAnnotationCalls(){
        log.info("Before Transactional Annotation calls");
    }

    @Before("combinedPointcut")
    public void beforeMyLoggingAnnotation(){
        log.info("Before mylogging annotation calls");
    }

    @After("combinedPointcut")
    public void afterMyLoggingAnnotation(){
        log.info("Before mylogging annotation calls");
    }

    @Pointcut("within(com.ganesh.AOP.services.impl.*) && @annotation(com.ganesh.AOP.aspect.MyLogging) || execution(* com.ganesh.AOP.services.impl.*.*(..))")
    public void combinedPointcut(){
    }
}
