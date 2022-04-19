package com.xh.readapp.timedTask;

import com.xh.readapp.service.ArticleLikeService;
import com.xh.readapp.service.RedisLikeService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
  
/**  
 * 点赞的定时任务  
 */  
@Slf4j  
public class LikeTask extends QuartzJobBean {

    @Autowired
    private RedisLikeService redisLikeService;

    @Autowired
    private ArticleLikeService articleLikeService;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    @Transactional
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        articleLikeService.transLikedFromRedis();
        redisLikeService.deleteMapArticleLikedCount();

        articleLikeService.transLikedCountFromRedis();
        redisLikeService.deleteMapUserLiked();

        log.info("LikeTask-------- {}", dtf.format(LocalDateTime.now()));
    }
}
