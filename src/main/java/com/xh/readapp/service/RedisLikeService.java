package com.xh.readapp.service;

import com.xh.readapp.domain.ArticleLike;
import com.xh.readapp.vo.redisLike.LikeCountsVo;

import java.util.List;

public interface RedisLikeService {

    /**
     * 若redis中没有点赞数key,则创建
     * @param articleId 文章的id
     */
    void createRedisKey(String articleId);

    /**
     * 将用户点赞的状态保存到redis中
     * @param articleId 文章id
     * @param userId 用户的id
     */
    void save(String articleId, String userId);

    /**
     * 将用户取消点赞后的状态保存到redis中
     * @param articleId 文章id
     * @param userId 用户的id
     */
    void nuSave(String articleId, String userId);

    /**
     * 点赞
     * @param articleId 文章id
     * @return 点赞后的数据
     */
    Integer like(String articleId);

    /**
     * 取消点赞
     * @param articleId 文章id
     * @return 取消点赞后的数据
     */
    Integer nuLike(String articleId);

    /**
     * 查询用户的点赞状态
     * @param articleId 文章id
     * @param userId  用户id
     * @return 返回用户的登录状态
     */
    Integer getStatus(String articleId, String userId);

    /**
     * 获取redis中的文章的点赞数
     * @return 文章的点赞数集合
     */
    List<LikeCountsVo> getArticleLikeCountsByRedis();

    /**
     * 获取redis中用户的点赞状态
     * @return 点赞状态集合
     */
    List<ArticleLike> getArticleLikeStatusByRedis();

    /**
     * 删除hashKey为MAP_USER_LIKED的缓存
     */
    void deleteMapUserLiked();

    /**
     * 删除hashKey为MAP_ARTICLE_LIKED_COUNT的缓存
     */
    void deleteMapArticleLikedCount();

    /**
     * 获取单个文章的点赞数从redis中
     * @param articleId 文章的id
     * @return 文章的点赞数
     */
    Integer getLikeCountsByRedis(String articleId);
}
