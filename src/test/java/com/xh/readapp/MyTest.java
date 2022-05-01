package com.xh.readapp;

import com.xh.readapp.domain.ArticleLike;
import com.xh.readapp.service.RedisLikeService;
import com.xh.readapp.vo.redisLike.LikeCountsVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyTest {

    @Autowired
    private RedisLikeService redisLikeService;

    @Test
    public void test1(){
        List<LikeCountsVo> articleLikeCountsByRedis = redisLikeService.getArticleLikeCountsByRedis();
        System.out.println(articleLikeCountsByRedis);
    }

    @Test
    public void test2(){
        List<ArticleLike> articleLikeStatusByRedis = redisLikeService.getArticleLikeStatusByRedis();
        for (ArticleLike articleLike : articleLikeStatusByRedis){
            System.out.println(articleLike.getArticleId());
            System.out.println(articleLike.getStatus());
            System.out.println(articleLike.getUserId());
            System.out.println();
        }
    }

    @Test
    public void test3(){
        List<LikeCountsVo> articleLikeCountsByRedis = redisLikeService.getArticleLikeCountsByRedis();
        for (LikeCountsVo likeCountsVo : articleLikeCountsByRedis){
            System.out.println(likeCountsVo.getLikeCounts());
            System.out.println(likeCountsVo.getArticleId());
        }
    }
}
