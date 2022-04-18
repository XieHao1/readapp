package com.xh.readapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xh.readapp.domain.Article;


public interface ArticleDao extends BaseMapper<Article> {

    /**
     * 查询文章
     * @param iPage 分页
     * @param categoryId 类别id
     * @param tagId 标签id
     * @return 分页后的数据
     */
    IPage<Article> findArticles(IPage<Article> iPage, String categoryId, String tagId);

    /**
     * 查询商家的文章
     * @return 分页后的商家文章
     */
    IPage<Article> findBusinessArticles(IPage<Article> iPage);

    /**
     * 查询指定作者的文章
     * @param iPage 分页参数
     * @param userId 用户id
     * @return 作者的文章集合
     */
    IPage<Article> findArticlesByUserId(IPage<Article> iPage, String userId);

    /**
     * 搜索文章的信息通过文章的名字
     * @param iPage 分页参数
     * @param articleName 文章的名字或者内容
     * @return 文章的的相关信息
     */

    IPage<Article> findArticleByArticleName(IPage<Article> iPage,String articleName);

}
