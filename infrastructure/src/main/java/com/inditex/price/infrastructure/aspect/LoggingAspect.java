package com.inditex.price.infrastructure.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("within(com.inditex.price.infrastructure.controller..*)")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Inicio del método: {} con argumentos: {}",
                joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "within(com.inditex.price.infrastructure.controller..*)", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Método: {} retornó: {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "within(com.inditex.price.infrastructure.controller..*)", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("Método: {} lanzó excepción: {}", joinPoint.getSignature(), exception.getMessage());
    }
}
