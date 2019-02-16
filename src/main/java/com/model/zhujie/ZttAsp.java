package com.model.zhujie;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ZttAsp {


    @Pointcut("@annotation(com.model.zhujie.Ztt)")
    public void servicePointcut(ProceedingJoinPoint pjp) {

    }

    @After("servicePointcut(ztt)")
    public void doAfter(ProceedingJoinPoint pjp) throws NoSuchMethodException {

        Class[] parameterTypes = new Class[pjp.getArgs().length];
        Object[] args = pjp.getArgs();
        for(int i=0; i<args.length; i++) {
            if(args[i] != null) {
                parameterTypes[i] = args[i].getClass();
            }else {
                parameterTypes[i] = null;
            }
        }
        String methodName = pjp.getSignature().getName();
        Method method = pjp.getSignature().getDeclaringType().getMethod(methodName, parameterTypes);



        /*Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        Method realMethod = pjp.getTarget().getClass().getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes());*/

    }



}
