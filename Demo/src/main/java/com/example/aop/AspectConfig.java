package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Before;
// import org.aspectj.lang.annotation.After;
// import org.aspectj.lang.annotation.AfterReturning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AspectConfig {
    Logger logger = LoggerFactory.getLogger(AspectConfig.class);

    // @Before(value = "execution(* com.example.controller.*.*(..))")
    // public void beforeAdvice(JoinPoint joinPoint){
    //     logger.info("Inside before advice");
    // }
    // @Before(value = "execution(* com.example.controller.*.*(..)) and args(object)")
    // public void beforeAdvice(JoinPoint joinPoint,Object object){
    //     logger.info("Request: {}", object);
    // }
    // @After(value = "execution(* com.example.controller.*.*(..)) and args(object)")
    // public void afterAdvice(JoinPoint joinPoint,Object object){
    //     logger.info("Request: {}", object);
    // }

    // @AfterReturning(value = "execution(* com.example.controller.*.*(..)) and args(object)",returning = "result")
    // public void afterReturningAdvice(JoinPoint joinPoint,Object object,Object result){
    //     logger.info("Request: {}", object);
    //     logger.info("Result: {}", result);
    // }

    @Around(value = "execution(* com.example.controller.*.*(..)) and args(object)")
    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint,Object object){
        logger.info("Request: {}", object);

        Object result=null;
        try {
            result=proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            logger.error("Error: {}", e);
        }

        logger.info("Result: {}", result);
    }
}