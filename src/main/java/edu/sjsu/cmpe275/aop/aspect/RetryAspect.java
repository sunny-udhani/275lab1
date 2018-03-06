package edu.sjsu.cmpe275.aop.aspect;

import edu.sjsu.cmpe275.aop.TweetStatsImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.aspectj.lang.annotation.Around;

import java.io.IOException;

@Aspect
@Order(1)
public class RetryAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */


    @Around("execution(public void edu.sjsu.cmpe275.aop.TweetService.*(..))")
    public Object retryAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        IOException exception = null;
        boolean retry = false;
        for (int i = 1; i <= 4; i++) {
            if (retry) {
                System.out.println("Network Error: Retry #" + (i - 1));
            }
            try {
//                System.out.println("proceed !!! - " + i);
                return joinPoint.proceed();
            } catch (IOException e) {
                System.out.println("Network Exception!!");
                exception = e;
                retry = true;
            }
        }
        return joinPoint;
    }

//    @AfterThrowing(pointcut = "execution(public void edu.sjsu.cmpe275.aop.TweetService.*(..))", throwing = "ex")
//    public void dummyAdvice1(ProceedingJoinPoint joinPoint, IOException ex) {
//        System.out.printf("Prior to the executuion of the metohd %s\n", joinPoint.getSignature().getName());
//
//        Object result = null;
//        try {
//            result = joinPoint.proceed();
//            System.out.printf("Finished the executuion of the method %s with result %s\n", joinPoint.getSignature().getName(), result);
//        } catch (Throwable e) {
//            e.printStackTrace();
//            System.out.printf("Aborted the executuion of the method %s\n", joinPoint.getSignature().getName());
//        }
//    }

}
