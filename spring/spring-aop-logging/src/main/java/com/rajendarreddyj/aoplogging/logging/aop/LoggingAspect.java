package com.rajendarreddyj.aoplogging.logging.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Around("execution(public * com.rajendarreddyj.aoplogging..*(..))")
    public Object log(final ProceedingJoinPoint pjp) throws Throwable {
        Logger log = LoggerFactory.getLogger(pjp.getTarget().getClass());
        Object[] args = pjp.getArgs();
        String methodName = pjp.getSignature().getName();
        if (!this.isUtilMethod(methodName)) {
            log.debug("{}(): {}", methodName, args);
        }
        Object result = pjp.proceed();
        if (!this.isUtilMethod(methodName)) {
            log.debug("{}(): result={}", methodName, result);
        }
        return result;
    }

    private boolean isUtilMethod(final String name) {
        return (name.startsWith("get")) || (name.startsWith("set")) || (name.equals("toString")) || (name.equals("equals")) || (name.equals("hashCode"));
    }
}