package com.elis.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {


    public static final String EXIT_WITH_RESULT = "Exit: {}.{}() with result = {}";
    public static final String ENTER_WITH_ARGUMENT_S = "Enter: {}.{}() with argument[s] = {}";
    public static final String EXCEPTION_IN_WITH_CAUSE = "Exception in {}.{}() with cause = {}";


    @Pointcut("within(@com.elis.common.annotation.PersistenceAdapter *)" +
            " || within(@com.elis.common.annotation.WebAdapter *)" +
            " || within(@com.elis.common.annotation.UseCase *)")
    public void elisBeanPointcut() {

    }
    @AfterThrowing(pointcut = "elisBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error(EXCEPTION_IN_WITH_CAUSE, joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getMessage());
    }

    @Around("elisBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String Args = Arrays.toString(joinPoint.getArgs());

        logFormat(ENTER_WITH_ARGUMENT_S, className, methodName, Args);
        Object result = joinPoint.proceed();
        logFormat(EXIT_WITH_RESULT, className, methodName, result);
        return result;
    }

    private void logFormat(String format, String className, String methodName, Object ArgsOrResult) {
        if(log.isDebugEnabled()){
            log.debug(format, className, methodName, ArgsOrResult);
        }
        log.info(format, className, methodName, ArgsOrResult);
    }
}
