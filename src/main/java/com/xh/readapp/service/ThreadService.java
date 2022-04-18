package com.xh.readapp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xh.readapp.dao.ArticleDao;
import com.xh.readapp.dao.CommentDao;
import com.xh.readapp.dao.FocusDao;
import com.xh.readapp.domain.Article;
import com.xh.readapp.domain.Comment;
import com.xh.readapp.domain.Focus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Async("taskExecutor")
public class ThreadService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private FocusDao focusDao;

    @Autowired
    private UserService userService;

    /**
     * 更新阅读数
     * @param article 文章的对应数据
     */
    public void updateArticleViewCounts(Article article) {
        Integer viewCounts = article.getViewCounts();
        Article updateArticle = new Article();
        updateArticle.setViewCounts(viewCounts+1);
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getArticleId,article.getArticleId());
        //进行双重判断，保证多线程情况下的线程安全
        lambdaQueryWrapper.eq(Article::getViewCounts,viewCounts);
        articleDao.update(updateArticle, lambdaQueryWrapper);
    }

    /**
     * 跟新文章表中的评论数
     * @param comment 评论相关信息
     */
    public void updateArticleCommentCounts(Comment comment) {
        String articleId = comment.getArticleId();
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getArticleId,articleId);
        Long count = commentDao.selectCount(lambdaQueryWrapper);
        Article article = new Article();
        article.setCommentCounts(count.intValue());
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getArticleId,articleId);
        articleDao.update(article,articleLambdaQueryWrapper);
    }

    /**
     * 更新用户的关注数和粉丝数
     * @param userId 用户id
     * @param toUserId 被关注用户的id
     */
    public void updateUserFocus(String userId, String toUserId) {
        //获取用户的关注数
        LambdaQueryWrapper<Focus> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Focus::getUserId,userId);
        Integer attentionCounts = focusDao.selectCount(lambdaQueryWrapper).intValue();
        //更新用户的关注数
        userService.updateUserAttentionCounts(userId,attentionCounts);
        //获取被关注用户的粉丝数
        lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Focus::getToUserId,toUserId);
        Integer fansCounts = focusDao.selectCount(lambdaQueryWrapper).intValue();
        //更新被关注人的粉丝数
        userService.updateUserFansCounts(toUserId,fansCounts);
    }
}
