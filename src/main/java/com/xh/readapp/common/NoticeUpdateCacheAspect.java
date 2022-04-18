package com.xh.readapp.common;

import com.xh.readapp.dictionary.ExchangeNameEnum;
import com.xh.readapp.util.RecordLogUtil;
import com.xh.readapp.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class NoticeUpdateCacheAspect {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Pointcut("@annotation(com.xh.readapp.common.NoticeUpdateCache)")
    public void pt(){}

    @AfterReturning("pt()")
    public void sendNotificationMessage(JoinPoint pjp){
        long start = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        NoticeUpdateCache annotation = method.getAnnotation(NoticeUpdateCache.class);
        String name = annotation.name();
        String routingKey = annotation.routingKey();
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUIDUtil.getUUID());
        rabbitTemplate.convertAndSend(ExchangeNameEnum.EXCHANGE_NAME.getExchangeName(),routingKey,name,correlationData);
        log.info("发送消息:{},发送的路由key:{}",name,routingKey);
        long end = System.currentTimeMillis();
        RecordLogUtil.recordLog(pjp,end-start);
    }
}
