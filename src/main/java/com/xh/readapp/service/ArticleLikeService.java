package com.xh.readapp.service;


public interface ArticleLikeService {
    /**
     * 获取用户在文章是否点赞
     * @param articleId 文章id
     * @param userId 用户id
     * @return 点赞状态
     */
    Integer getStatus(String articleId, String userId);

    /**
     * 添加
     * @param articleId 文章id
     * @param userId 用户id
     */
    void insertArticleLike(String articleId, String userId);

    /**
     * 将redis中的点赞数据保存到数据库中
     */
    void transLikedFromRedis();

    /**
     * 将redis中的点赞数保存到数据库中
     */
    void transLikedCountFromRedis();
}
