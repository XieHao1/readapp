package com.xh.readapp.Controller;

import com.xh.readapp.common.Cache;
import com.xh.readapp.common.NoticeUpdateCache;
import com.xh.readapp.service.ArticleService;
import com.xh.readapp.service.DraftService;
import com.xh.readapp.util.ResultJson;
import com.xh.readapp.vo.articles.PageParams;
import com.xh.readapp.vo.articles.ArticlePageParams;
import com.xh.readapp.vo.articles.SearchArticleParams;
import com.xh.readapp.vo.draft.DraftParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private DraftService draftService;

    @PostMapping()
    @Cache(expire=30*60*1000,name="查看文章")
    public ResultJson findAllArticles(@RequestBody PageParams pageParams){
        return articleService.findAllArticles(pageParams);
    }

    @PostMapping("/view")
    @Cache(name="查看个人文章")
    public ResultJson findArticleByArticleId(String articleId){
        return articleService.findArticleOneByArticleId(articleId,true);
    }

    @PostMapping("/like")
    @NoticeUpdateCache(name="更新文章缓存",routingKey = "article")
    public ResultJson likeArticle(String articleId){
        return articleService.likeArticle(articleId);
    }

    @PostMapping("/unLike")
    @NoticeUpdateCache(name="更新文章缓存",routingKey = "article")
    public ResultJson nuLikeArticle(String articleId){
        return articleService.unLikeArticle(articleId);
    }

    @PostMapping("/publish")
    @NoticeUpdateCache(name="更新文章缓存",routingKey = "article")
    public ResultJson publish(@RequestBody ArticlePageParams pageParams){
        return articleService.insertArticle(pageParams);
    }

    @PostMapping("/myArticles")
    @Cache(expire=30*60*1000,name="查看我的文章")
    public ResultJson myArticles(@RequestBody PageParams pageParams){
        return articleService.findMyArticlesByUserId(pageParams);
    }

    @PostMapping("/edit")
    @NoticeUpdateCache(name="更新文章缓存",routingKey = "article")
    public ResultJson edit(@RequestBody ArticlePageParams pageParams){
        return articleService.updateArticleByArticleId(pageParams);
    }

    @PostMapping("/draft")
    public ResultJson draft(@RequestBody DraftParams draftParams){
        return draftService.insertDraft(draftParams);
    }

    @PostMapping("/getDraft")
    public ResultJson getDraft(String userId){
        return draftService.findDraftByUserId(userId);
    }

    @PostMapping("/business")
    @Cache(expire=30*60*1000,name="查看商家文章")
    public ResultJson businessArticles(@RequestBody PageParams pageParams){
        return articleService.findBusinessArticles(pageParams);
    }

    @PostMapping("/search")
    @Cache(expire=30*60*1000,name="查看搜索后的文章")
    public ResultJson searchArticle(@RequestBody SearchArticleParams searchArticleParams){
        return articleService.findArticleByArticleName(searchArticleParams);
    }
}
