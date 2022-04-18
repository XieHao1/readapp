package com.xh.readapp.common;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoticeUpdateCache {

    String name() default "更新缓存";
    //发送消息的路由
    String routingKey();
}
