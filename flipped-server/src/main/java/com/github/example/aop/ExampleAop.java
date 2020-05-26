package com.github.example.aop;

import com.alibaba.fastjson.JSON;
import com.github.common.CommonConstant;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
@Slf4j
public class ExampleAop {

    /**
     * execution() -- 表达式的主体
     * 第一个“*”符号 -- 表示返回值的类型任意
     * com.github.example -- AOP所切的服务的包名
     * 包名后面的“..” -- 表示当前包及子包
     * 第二个“*” -- 表示类名，*即所有类
     * .*(..) -- 表示任何方法名，括号表示参数，两个点表示任何参数类型
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("execution(public * com.github.example.service..*.get*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        CommonConstant.threadLocal.set(Lists.newArrayList());
        log.info("around name: {}", proceedingJoinPoint.getSignature().getName());
        try {
            Object obj = proceedingJoinPoint.proceed();
            List<String> result = CommonConstant.threadLocal.get();
            log.info(JSON.toJSONString(result));
            return obj;
        } catch (Throwable throwable) {
            log.info("AOP exception", throwable);
        }

        return null;
    }
}
