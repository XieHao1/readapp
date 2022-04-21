package com.xh.readapp.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class RecordLogUtil {
    public static void recordLog(JoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("======================log start==================");
        //获取时间
        String nowTime = TimeUtil.getStringTime();
        log.info("时间:{}",nowTime);
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}",className + "." + methodName + "()");

        //请求参数
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSON(args).toString();
        log.info("params:{}",params);

        //获取request
        HttpServletRequest httpServletRequest = HttpContextUtils.getHttpServletRequest();
        //获取ip地址
        String ipAddr = IpUtils.getIpAddr(httpServletRequest);
        log.info("ip:{}",ipAddr);

        log.info("方法消耗时间 time : {} ms",time);
        log.info("=====================log end====================");
        log.info("");
        log.info("");
    }
}
