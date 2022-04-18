package com.xh.readapp.service;

import com.xh.readapp.domain.Tag;
import com.xh.readapp.vo.articles.ArticleTagVo;

import java.util.List;

public interface ArticleTagService {
    /**
     * 通过文章的id查询文章对应的标签
     * @param articleId 文章的id
     * @return 文章的标签信息
     */
    List<Tag> findArticleTagsByArticleId(String articleId);

    /**
     * 添加文章和标签的对应关系
     * @param articleId 文章的id
     * @param tags 标签列表
     */
    void insertArticleTag(String articleId, List<ArticleTagVo> tags);


    /**
     * 通过文章的id修改文章的标签
     * @param articleId 文章的id
     * @param tags 文章的标签数据
     * @return 修改后影响的条数
     */
    int updateArticleTagsByArticleId(String articleId, List<ArticleTagVo> tags);
}
