package com.xh.readapp.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xh.readapp.dao.ArticleBodyDao;
import com.xh.readapp.domain.ArticleBody;
import com.xh.readapp.service.ArticleBodyService;
import com.xh.readapp.vo.articles.ArticleBodyParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleBodyServiceImpl implements ArticleBodyService {

    @Autowired
    private ArticleBodyDao articleBodyDao;

    @Override
    public ArticleBody findArticleContentByArticleId(String articleId) {
        LambdaQueryWrapper<ArticleBody> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArticleBody::getArticleId,articleId);
        lambdaQueryWrapper.select(ArticleBody::getContent,ArticleBody::getContentHtml);
        return articleBodyDao.selectOne(lambdaQueryWrapper);
    }

    @Override
    public void insertArticleBody(ArticleBody articleBody) {
        articleBodyDao.insert(articleBody);
    }

    @Override
    public int updateArticleBodyByBodyId(String bodyId, ArticleBodyParams body) {
        String contextHtml = body.getContentHtml();
        String content = body.getContent();
        ArticleBody articleBody = new ArticleBody();
        articleBody.setContentHtml(contextHtml);
        articleBody.setContent(content);
        LambdaQueryWrapper<ArticleBody> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArticleBody::getBodyId,bodyId);
        return articleBodyDao.update(articleBody, lambdaQueryWrapper);
    }
}
