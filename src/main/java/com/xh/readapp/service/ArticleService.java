package com.xh.readapp.service;

import com.xh.readapp.domain.ArticleLike;
import com.xh.readapp.util.ResultJson;
import com.xh.readapp.vo.articles.PageParams;
import com.xh.readapp.vo.articles.ArticlePageParams;
import com.xh.readapp.vo.articles.SearchArticleParams;
import com.xh.readapp.vo.redisLike.LikeCountsVo;

import java.util.List;

public interface ArticleService {
    /**
     * 查询所有文章
     * @param pageParams 文章的分页参数
     * @return 文章的相关信息
     */
    ResultJson findAllArticles(PageParams pageParams);

    /**
     * 通过文章id查询相关文章内容
     * @param articleId 文章id
     * @return 该文章的相关信息
     */
    ResultJson findArticleOneByArticleId(String articleId,boolean isUpdateViewCounts);

    /**
     * 更新文章的点赞数
     * @param articleId 文章的id
     * @return 更新后的点赞数
     */
    ResultJson likeArticle(String articleId);

    /**
     * 增加文章
     * @param pageParams 增加文章的参数
     * @return 文章的id
     */
    ResultJson insertArticle(ArticlePageParams pageParams);

    /**
     * 通过用户的查询用户所有文章的点赞数
     * @param userId 用户id
     * @return 总点赞数
     */
    Integer countLikeCountsByUserId(String userId);

    /**
     * 通过用户id查询用户自己的文章
     * @param pageParams 用户的id和分页参数
     * @return 用户的文章信息
     */
    ResultJson findMyArticlesByUserId(PageParams pageParams);

    /**
     * 修改文章
     * @param pageParams 修改文章的参数
     * @return 更新后的文章信息
     */
    ResultJson updateArticleByArticleId(ArticlePageParams pageParams);

    /**
     * 查询商家的文章
     * @param pageParams 文章的分页参数
     * @return 商家的所有文章
     */
    ResultJson findBusinessArticles(PageParams pageParams);

    /**
     * 取消点赞
     * @param articleId 文章id
     * @return 返回取消点赞后的点赞数
     */
    ResultJson unLikeArticle(String articleId);

    /**
     * 获取文章的点赞数
     * @param articleId 文章的id
     * @return 文章的点赞数
     */
    Integer getLikeCounts(String articleId);

    /**
     * 更新文章的点赞数
     * @param articleLikeCounts 文章的id和文章的点赞数
     */
    void updateArticleLikeCounts(List<LikeCountsVo> articleLikeCounts);

    /**
     * 通过文章的内容或者文章的名字进行模糊搜索
     * @param searchArticleParams 文章的名字或内容以及分页消息
     * @return 文章的id,文章的名字
     */
    ResultJson findArticleByArticleName(SearchArticleParams searchArticleParams);
}
