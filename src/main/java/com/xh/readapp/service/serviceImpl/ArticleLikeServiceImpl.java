package com.xh.readapp.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xh.readapp.dao.ArticleLikeDao;
import com.xh.readapp.domain.ArticleLike;
import com.xh.readapp.service.ArticleLikeService;
import com.xh.readapp.service.ArticleService;
import com.xh.readapp.service.RedisLikeService;
import com.xh.readapp.util.UUIDUtil;
import com.xh.readapp.vo.redisLike.LikeCountsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ArticleLikeServiceImpl implements ArticleLikeService {

    @Autowired
    private ArticleLikeDao articleLikeDao;

    @Autowired
    private RedisLikeService redisLikeService;

    @Autowired
    private ArticleService articleService;

    @Override
    @Transactional
    public Integer getStatus(String articleId, String userId) {
        LambdaQueryWrapper<ArticleLike> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(ArticleLike::getStatus);
        lambdaQueryWrapper.eq(ArticleLike::getArticleId,articleId).eq(ArticleLike::getUserId,userId);
        ArticleLike articleLike = articleLikeDao.selectOne(lambdaQueryWrapper);
        if(articleLike == null){
            insertArticleLike(articleId,userId);
            return 0;
        }
        return articleLike.getStatus();

    }

    @Override
    public void insertArticleLike(String articleId, String userId) {
        //如果表中没有该数据，则添加
        ArticleLike insertArticleLike = new ArticleLike();
        insertArticleLike.setArticleLikeId(UUIDUtil.getUUID());
        insertArticleLike.setArticleId(articleId);
        insertArticleLike.setUserId(userId);
        articleLikeDao.insert(insertArticleLike);
    }

    @Override
    @Transactional
    public void transLikedFromRedis() {
        List<ArticleLike> articleLikeStatusByRedis = redisLikeService.getArticleLikeStatusByRedis();
        LambdaQueryWrapper<ArticleLike> lambdaQueryWrapper;
        for (ArticleLike articleLike : articleLikeStatusByRedis){
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ArticleLike::getArticleId,articleLike.getArticleId());
            lambdaQueryWrapper.eq(ArticleLike::getUserId,articleLike.getUserId());
            ArticleLike updateArticleLike = new ArticleLike();
            updateArticleLike.setStatus(articleLike.getStatus());
            articleLikeDao.update(updateArticleLike,lambdaQueryWrapper);
        }
    }


    @Override
    @Transactional
    public void transLikedCountFromRedis() {
        List<LikeCountsVo> articleLikeCountsByRedis = redisLikeService.getArticleLikeCountsByRedis();
        articleService.updateArticleLikeCounts(articleLikeCountsByRedis);
    }
}
