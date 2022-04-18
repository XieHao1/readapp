package com.xh.readapp.config;

import com.xh.readapp.dictionary.ExchangeNameEnum;
import com.xh.readapp.dictionary.QueueNameEnum;
import com.xh.readapp.dictionary.RoutingKeyEnum;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String EXCHANGE_NAME = ExchangeNameEnum.EXCHANGE_NAME.getExchangeName();
    private static final String BACKUP_EXCHANGE_NAME = ExchangeNameEnum.BACKUP_EXCHANGE_NAME.getExchangeName();

    private static final String ARTICLE_QUEUE_NAME = QueueNameEnum.ARTICLE_QUEUE_NAME.getQUEUE_NAME();
    private static final String WARNING_QUEUE_NAME = QueueNameEnum.WARNING_QUEUE_NAME.getQUEUE_NAME();
    private static final String DATA_QUEUE_NAME = QueueNameEnum.DATA_QUEUE_NAME.getQUEUE_NAME();
    private static final String COMMENT_QUEUE_NAME = QueueNameEnum.COMMENT_QUEUE_NAME.getQUEUE_NAME();
    private static final String HISTORY_QUEUE_NAME = QueueNameEnum.HISTORY_QUEUE_NAME.getQUEUE_NAME();

    private static final String ARTICLE_ROUTING_KEY = RoutingKeyEnum.ARTICLE_ROUTING_KEY.getRoutingKey();
    private static final String DATA_ROUTING_KEY = RoutingKeyEnum.DATA_ROUTING_KEY.getRoutingKey();
    private static final String COMMENT_ROUTING_KEY = RoutingKeyEnum.COMMENT_ROUTING_KEY.getRoutingKey();
    private static final String HISTORY_ROUTING_KEY = RoutingKeyEnum.HISTORY_ROUTING_KEY.getRoutingKey();

    @Bean
    public Queue historyQueue(){
        return new Queue(HISTORY_QUEUE_NAME,true,false,false);
    }
    @Bean
    public Binding historyQueueToAppExchange(Queue commentQueue,DirectExchange readAppExchange){
        return BindingBuilder.bind(commentQueue).to(readAppExchange).with(HISTORY_ROUTING_KEY);
    }

    @Bean
    public Queue commentQueue(){
        return new Queue(COMMENT_QUEUE_NAME,true,false,false);
    }
    @Bean
    public Binding commentQueueToAppExchange(Queue commentQueue,DirectExchange readAppExchange){
        return BindingBuilder.bind(commentQueue).to(readAppExchange).with(COMMENT_ROUTING_KEY);
    }

    @Bean
    public Queue dataQueue(){
        return new Queue(DATA_QUEUE_NAME,true,false,false);
    }

    @Bean
    public Binding dataQueueToReadAppExchange(Queue dataQueue,DirectExchange readAppExchange){
        return BindingBuilder.bind(dataQueue).to(readAppExchange).with(DATA_ROUTING_KEY);
    }

    @Bean
    public DirectExchange readAppExchange(){
        return ExchangeBuilder.directExchange(EXCHANGE_NAME).durable(true).alternate(BACKUP_EXCHANGE_NAME).build();
    }

    @Bean
    public FanoutExchange backupExchange(){
        return new FanoutExchange(BACKUP_EXCHANGE_NAME,true,false);
    }

    @Bean
    public Queue articleQueue(){
        return new Queue(ARTICLE_QUEUE_NAME,true,false,false);
    }

    @Bean
    public Queue warningQueue(){
        return new Queue(WARNING_QUEUE_NAME,true,false,false);
    }

    @Bean
    public Binding articleQueueToReadAppExchange(Queue articleQueue,DirectExchange readAppExchange){
        return BindingBuilder.bind(articleQueue).to(readAppExchange).with(ARTICLE_ROUTING_KEY);
    }

    @Bean
    public Binding warningQueueToBackupExchange(Queue warningQueue,FanoutExchange backupExchange){
        return BindingBuilder.bind(warningQueue).to(backupExchange);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
