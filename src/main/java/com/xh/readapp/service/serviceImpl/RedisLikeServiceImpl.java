package com.xh.readapp.service.serviceImpl;

import com.xh.readapp.dictionary.LikedStatusEnum;
import com.xh.readapp.domain.ArticleLike;
import com.xh.readapp.service.ArticleLikeService;
import com.xh.readapp.service.ArticleService;
import com.xh.readapp.service.RedisLikeService;
import com.xh.readapp.util.RedisLikeKeyUtils;
import com.xh.readapp.vo.redisLike.LikeCountsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RedisLikeServiceImpl implements RedisLikeService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    @Lazy
    private ArticleService articleService;

    @Autowired
    @Lazy
    private ArticleLikeService articleLikeService;

    private static final String MAP_KEY_ARTICLE_LIKED_COUNT = RedisLikeKeyUtils.MAP_KEY_ARTICLE_LIKED_COUNT;
    private static final String MAP_KEY_USER_LIKED = RedisLikeKeyUtils.MAP_KEY_USER_LIKED;


    @Override
    public void createRedisKey(String articleId,String userId) {
        Boolean articleHasKey = redisTemplate.opsForHash().hasKey(MAP_KEY_ARTICLE_LIKED_COUNT,articleId);

        String likedKey = RedisLikeKeyUtils.getLikedKey(userId, articleId);
        Boolean userHasKey = redisTemplate.opsForHash().hasKey(MAP_KEY_USER_LIKED,likedKey);

        if(!articleHasKey){
            Integer likeCounts = articleService.getLikeCounts(articleId);
            redisTemplate.opsForHash().put(MAP_KEY_ARTICLE_LIKED_COUNT,articleId,String.valueOf(likeCounts));
        }

        if(!userHasKey){
            //获取文章的点赞状态
            Integer status = articleLikeService.getStatus(articleId, userId);
            redisTemplate.opsForHash().put(MAP_KEY_USER_LIKED,likedKey,String.valueOf(status));
        }

        log.info("缓存点赞数key:{},map_key:{}",MAP_KEY_ARTICLE_LIKED_COUNT,articleId);
    }

    @Override
    public Integer like(String articleId) {
        return redisTemplate.opsForHash().increment(MAP_KEY_ARTICLE_LIKED_COUNT,articleId,1).intValue();
    }

    @Override
    public Integer nuLike(String articleId) {
        return redisTemplate.opsForHash().increment(MAP_KEY_ARTICLE_LIKED_COUNT,articleId,-1).intValue();
    }

    @Override
    public void save(String articleId, String userId) {
        String likedKey = RedisLikeKeyUtils.getLikedKey(userId,articleId);
        redisTemplate.opsForHash().put(MAP_KEY_USER_LIKED,likedKey,String.valueOf(LikedStatusEnum.LIKE.getCode()));
        log.info("将用户的点赞记录存入redis中,redis的key:{},redis中map的key:{}",MAP_KEY_USER_LIKED,likedKey);
    }

    @Override
    public void nuSave(String articleId, String userId) {
        String likedKey = RedisLikeKeyUtils.getLikedKey(userId,articleId);
        redisTemplate.opsForHash().put(MAP_KEY_USER_LIKED,likedKey,String.valueOf(LikedStatusEnum.UNLIKE.getCode()));
        log.info("将用户的取消点赞记录存入redis中,redis的key:{},redis中map的key:{}",MAP_KEY_USER_LIKED,likedKey);
    }

    @Override
    public Integer getStatus(String articleId, String userId) {
        String likedKey = RedisLikeKeyUtils.getLikedKey(userId,articleId);
        Object o = redisTemplate.opsForHash().get(MAP_KEY_USER_LIKED,likedKey);
        if(o == null){
            return -1;
        }
        return Integer.parseInt(o.toString());
    }

    @Override
    public List<LikeCountsVo> getArticleLikeCountsByRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(MAP_KEY_ARTICLE_LIKED_COUNT, ScanOptions.NONE);
        List<LikeCountsVo> likeCountsVos = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            LikeCountsVo likeCountsVo  = new LikeCountsVo();
            likeCountsVo.setArticleId(entry.getKey().toString());
            likeCountsVo.setLikeCounts(Integer.parseInt(entry.getValue().toString()));
            likeCountsVos.add(likeCountsVo);
        }
        return likeCountsVos;
    }

    @Override
    public List<ArticleLike> getArticleLikeStatusByRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<ArticleLike> likeLists = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            ArticleLike articleLike = new ArticleLike();
            String str = entry.getKey().toString();
            String[] split = str.split("::");
            articleLike.setArticleId(split[1]);
            articleLike.setUserId(split[0]);
            articleLike.setStatus(Integer.parseInt(entry.getValue().toString()));
            likeLists.add(articleLike);
        }
        return likeLists;
    }

    @Override
    public void deleteMapUserLiked() {
        redisTemplate.delete(MAP_KEY_USER_LIKED);
    }

    @Override
    public void deleteMapArticleLikedCount() {
        redisTemplate.delete(MAP_KEY_ARTICLE_LIKED_COUNT);
    }

    @Override
    public Integer getLikeCountsByRedis(String articleId) {
        Object o = redisTemplate.opsForHash().get(MAP_KEY_ARTICLE_LIKED_COUNT, articleId);
        if(o == null) return null;
        return Integer.parseInt(o.toString());
    }

}
