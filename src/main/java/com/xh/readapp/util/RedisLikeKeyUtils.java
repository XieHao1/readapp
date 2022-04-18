package com.xh.readapp.util;

public class RedisLikeKeyUtils {

    //保存用户点赞数据的key
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";
    //保存文章被点赞数量的key
    public static final String MAP_KEY_ARTICLE_LIKED_COUNT = "MAP_ARTICLE_LIKED_COUNT";

    /**
     * 拼接点赞的用户id和点赞文章的id作为key。格式 222222::333333
     * @param likedUserId 点赞的人id
     * @param likedArticleId 点赞的文章的id
     * @return Redis的key
     */
    public static String getLikedKey(String likedUserId, String likedArticleId){
        return likedUserId + "::" + likedArticleId;
    }

}
