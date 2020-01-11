package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class PerfAspect {



//    @Around("execution(* com.example.boottest.controller.*(..))")
    @Around("@annotation(PerfLogging)")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {

        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        log.info(pjp.getSignature().getDeclaringTypeName()+"의 "+pjp.getSignature().getName() + " 메소드 실행 시간 : "+ (System.currentTimeMillis() - begin));
        return retVal;
    }
}
