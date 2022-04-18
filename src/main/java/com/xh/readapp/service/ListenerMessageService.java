package com.xh.readapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ListenerMessageService {

    @Autowired
    private UpdateCacheService updateCacheService;

    @RabbitListener(queues = "article.queue")
    public void listenerArticleMsg(@Payload String message){
        log.info("article.queue收到的消息为:{}",message);
        updateCacheService.deleteArticleCache();
    }

    @RabbitListener(queues = "data.queue")
    public void listenerDataMsg(@Payload String message){
        log.info("data.queue收到的消息为:{}",message);
        updateCacheService.deleteDataCache();
    }

    @RabbitListener(queues = "warning.queue")
    public void listenerWarningMsg(@Payload String message){
        log.info("warning.queue警告消息:{}",message);
    }

    @RabbitListener(queues = "comment.queue")
    public void listenerCommentMsg(@Payload String message){
        log.info("comment.queue收到消息为:{}",message);
        updateCacheService.deleteCommentCache();
    }

    @RabbitListener(queues = "history.queue")
    public void listenerHistoryMsg(@Payload String message){
        log.info("history.queue收到消息为:{}",message);
        updateCacheService.deleteHistoryCache();
    }
}
