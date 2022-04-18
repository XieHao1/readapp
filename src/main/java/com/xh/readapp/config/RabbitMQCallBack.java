package com.xh.readapp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
@Slf4j
public class RabbitMQCallBack implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("队列未收到的消息:{},路由key:{},交换机:{},未收到原因:{}",
                returned.getMessage(),
                returned.getRoutingKey(),
                returned.getExchange(),
                returned.getReplyText());
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack) {
            log.info("收到的消息id:{}", correlationData.getId());
        }
    }
}
