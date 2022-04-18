package com.xh.readapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class UpdateCacheService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public void deleteArticleCache(){
        Set<String> keys = redisTemplate.keys("查看*");
        if(null != keys){
            redisTemplate.delete(keys);
            log.info("删除所有查看文章缓存");
        }

    }

    public void deleteDataCache(){
        Set<String> keys = redisTemplate.keys("个人资料*");
        if(null != keys){
            redisTemplate.delete(keys);
            log.info("删除所有个人资料缓存");
        }

    }

    public void deleteCommentCache() {
        Set<String> keys = redisTemplate.keys("评论*");
        if(null != keys){
            redisTemplate.delete(keys);
            log.info("删除所有评论缓存");
        }
    }

    public void deleteHistoryCache() {
        Set<String> keys = redisTemplate.keys("阅读室*");
        if(null != keys){
            redisTemplate.delete(keys);
            log.info("删除所有阅读室缓存");
        }
    }
}
