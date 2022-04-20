package com.xh.readapp.service;

import com.xh.readapp.domain.ArticleBody;
import com.xh.readapp.vo.articles.ArticleBodyParams;

public interface ArticleBodyService {
    /**
     * 通过文章id查询文章的内容
     * @param articleId 文章的id
     * @return 文章的内容
     */
    ArticleBody findArticleContentByArticleId(String articleId);

    /**
     * 添加文章内容
     * @param articleBody 要添加的文章类容对象
     */
    void insertArticleBody(ArticleBody articleBody);

    /**
     * 通过bodyId修改文章的内容
     * @param bodyId bodyId
     * @param body 要修改的body数据
     * @return 更新影响的行数
     */
    int updateArticleBodyByBodyId(String bodyId, ArticleBodyParams body);
}
