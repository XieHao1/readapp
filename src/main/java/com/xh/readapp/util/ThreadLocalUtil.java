package com.xh.readapp.util;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ThreadLocalUtil {

    private ThreadLocalUtil(){}

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();


    public static void putThread(String userId){
        THREAD_LOCAL.set(userId);
    }

    public static String getThread(){
        return THREAD_LOCAL.get();
    }

    public static void removeThread(){
        THREAD_LOCAL.remove();
    }
}
