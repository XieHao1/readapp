package com.xh.readapp.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xh.readapp.dao.ArticleDao;
import com.xh.readapp.dictionary.ErrorEnum;
import com.xh.readapp.domain.*;
import com.xh.readapp.service.*;
import com.xh.readapp.util.ResultJson;
import com.xh.readapp.util.ThreadLocalUtil;
import com.xh.readapp.util.TimeUtil;
import com.xh.readapp.util.UUIDUtil;
import com.xh.readapp.vo.articles.*;
import com.xh.readapp.vo.redisLike.LikeCountsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleBodyService articleBodyService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ThreadService threadService;

    @Autowired
    @Lazy
    private ArticleLikeService articleLikeService;

    @Autowired
    private RedisLikeService redisLikeService;

    @Override
    public ResultJson findAllArticles(PageParams pageParams) {
        IPage<Article> iPage = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        IPage<Article> iPage1 = articleDao.findArticles(iPage,pageParams.getCategoryId(),pageParams.getTagId());
        Integer pages = (int)iPage1.getPages();

        if(pages < pageParams.getPage()){
            return ResultJson.success(null);
        }
        List<Article> records = iPage1.getRecords();
        List<ArticleVo> articleVoList = copyList(records,true,true);
        ArticlePagesVo articlePagesVo = new ArticlePagesVo();
        articlePagesVo.setPages(pages);
        articlePagesVo.setArticles(articleVoList);

        return ResultJson.success(articlePagesVo);
    }

    @Override
    public ResultJson findArticleOneByArticleId(String articleId,boolean isUpdateViewCounts) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getArticleId,articleId);
        Article article = articleDao.selectOne(lambdaQueryWrapper);
        //在其它线程中进行阅读数的更新操作
        if(isUpdateViewCounts){
            threadService.updateArticleViewCounts(article);
        }
        return ResultJson.success(copy(article,true,true));
    }

    @Override
    @Transactional
    public ResultJson likeArticle(String articleId) {
        String userId = ThreadLocalUtil.getThread();
        redisLikeService.createRedisKey(articleId,userId);

        //获取用户点赞状态
        Integer status = getStatus(articleId,userId);

        if(status == 1){
            return ResultJson.fail(ErrorEnum.ALREADY_LIKE_COUNTS_ERROR.getCode(),ErrorEnum.ALREADY_LIKE_COUNTS_ERROR.getMsg());
        }

        //点赞
        Integer increment = redisLikeService.like(articleId);

        //保存用户的点赞状态
        redisLikeService.save(articleId,userId);

        Map<String,Integer> map = new HashMap<>();
        map.put("likeCounts",increment);
        map.put("status",getStatus(articleId,userId));
        return ResultJson.success(map);
    }

    @Override
    @Transactional
    public ResultJson unLikeArticle(String articleId) {
        String userId = ThreadLocalUtil.getThread();
        redisLikeService.createRedisKey(articleId,userId);
        //获取用户点赞状态
        Integer status = getStatus(articleId,userId);
        if(status == 0){
            return ResultJson.fail(ErrorEnum.NOT_LIKE_COUNTS_ERROR.getCode(),
                    ErrorEnum.NOT_LIKE_COUNTS_ERROR.getMsg());
        }
        //取消点赞
        Integer decrement = redisLikeService.nuLike(articleId);
        //保存用户取消点赞的状态
        redisLikeService.nuSave(articleId,userId);
        Map<String,Integer> map = new HashMap<>();
        map.put("likeCounts",decrement);
        map.put("status",getStatus(articleId,userId));
        return ResultJson.success(map);
    }

    @Override
    public Integer getLikeCounts(String articleId) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getArticleId,articleId);
        lambdaQueryWrapper.select(Article::getLikeCounts);
        return articleDao.selectOne(lambdaQueryWrapper).getLikeCounts();
    }

    @Override
    public void updateArticleLikeCounts(List<LikeCountsVo> articleLikeCounts) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper;
        for (LikeCountsVo likeCountsVo : articleLikeCounts){
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Article::getArticleId,likeCountsVo.getArticleId());
            Article article = new Article();
            article.setLikeCounts(likeCountsVo.getLikeCounts());
            articleDao.update(article,lambdaQueryWrapper);
        }
    }

    @Override
    public ResultJson findArticleByArticleName(SearchArticleParams searchArticleParams) {
        String article = searchArticleParams.getArticle().trim();
        Integer page = searchArticleParams.getPage();
        Integer pageSize = searchArticleParams.getPageSize();

        IPage<Article> iPage = new Page<>(page,pageSize);
        IPage<Article> articleByArticleTitle = articleDao.findArticleByArticleName(iPage, article);
        //总页数
        Integer pages = (int)iPage.getPages();

        if(pages < page){
            return ResultJson.success(null);
        }
        List<Article> articleTitle = articleByArticleTitle.getRecords();
        List<ArticleVo> articleTitleVoList = copyList(articleTitle, true, true);

        return ResultJson.success(articleTitleVoList);
    }

    @Override
    @Transactional
    public ResultJson insertArticle(ArticlePageParams pageParams) {
        String categoryId = pageParams.getCategoryId();
        String userId = pageParams.getUserId();
        String title = pageParams.getTitle();
        List<ArticleTagVo> tags = pageParams.getTags();
        String content = pageParams.getBody().getContent();
        String contextHtml = pageParams.getBody().getContentHtml();
        String audioUrl = pageParams.getAudioUrl();

        //article
        Article article = new Article();
        String articleId = UUIDUtil.getUUID();
        article.setArticleId(articleId);
        article.setCreateDate(TimeUtil.getStringTime());
        article.setTitle(title);
        article.setUserId(userId);
        article.setCategoryId(categoryId);
        article.setAudioUrl(audioUrl);
        articleDao.insert(article);

        //articleBody
        ArticleBody articleBody = new ArticleBody();
        String bodyId = UUIDUtil.getUUID();
        articleBody.setArticleId(articleId);
        articleBody.setBodyId(bodyId);
        articleBody.setContent(content);
        articleBody.setContentHtml(contextHtml);
        articleBodyService.insertArticleBody(articleBody);

        //articleTag
        articleTagService.insertArticleTag(articleId,tags);

        //更新bodyId
        Article article1 = new Article();
        article1.setBodyId(bodyId);
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getArticleId,articleId);
        articleDao.update(article1,lambdaQueryWrapper);
        Map<String,String> map = new HashMap<>();
        map.put("articleId",articleId);
        return ResultJson.success(map);
    }

    @Override
    public Integer countLikeCountsByUserId(String userId) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(Article::getLikeCounts);
        lambdaQueryWrapper.eq(Article::getUserId,userId);
        List<Article> articles = articleDao.selectList(lambdaQueryWrapper);
        return articles.stream().mapToInt(Article::getLikeCounts).sum();
    }

    @Override
    public ResultJson findMyArticlesByUserId(PageParams pageParams) {
        String userId = pageParams.getUserId();
        Integer page = pageParams.getPage();
        IPage<Article> iPage = new Page<>(page, pageParams.getPageSize());
        IPage<Article> page1 = articleDao.findArticlesByUserId(iPage,userId);
        int pages = (int) page1.getPages();
        if(pages < page){
            return ResultJson.success(null);
        }
        List<Article> articles = page1.getRecords();
        List<ArticleVo> articleVoList = copyList(articles,false,true);
        ArticlePagesVo articlePagesVo = new ArticlePagesVo();
        articlePagesVo.setPages(pages);
        articlePagesVo.setArticles(articleVoList);
        return ResultJson.success(articlePagesVo);
    }

    @Override
    @Transactional
    public ResultJson updateArticleByArticleId(ArticlePageParams pageParams) {

        String articleId = pageParams.getArticleId();
        String title = pageParams.getTitle();
        ArticleBodyParams body = pageParams.getBody();
        List<ArticleTagVo> tags = pageParams.getTags();
        String categoryId = pageParams.getCategoryId();

        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getArticleId,articleId);
        Article article = new Article();
        article.setTitle(title);
        article.setCategoryId(categoryId);
        int update = articleDao.update(article, lambdaQueryWrapper);
        if(update == 0){
            return ResultJson.fail(ErrorEnum.UPDATE_ARTICLE_ERROR.getCode(), ErrorEnum.UPDATE_ARTICLE_ERROR.getMsg());
        }

        //查询bodyId
        lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getArticleId,articleId);
        lambdaQueryWrapper.select(Article::getBodyId);
        Article article1 = articleDao.selectOne(lambdaQueryWrapper);
        String bodyId = article1.getBodyId();

        //修改body
        int i = articleBodyService.updateArticleBodyByBodyId(bodyId, body);
        if(i == 0){
            return ResultJson.fail(ErrorEnum.UPDATE_ARTICLE_ERROR.getCode(), ErrorEnum.UPDATE_ARTICLE_ERROR.getMsg());
        }

        //修改标签信息
        int j = articleTagService.updateArticleTagsByArticleId(articleId,tags);
        if(j == 0){
            return ResultJson.fail(ErrorEnum.UPDATE_ARTICLE_ERROR.getCode(), ErrorEnum.UPDATE_ARTICLE_ERROR.getMsg());
        }
        return this.findArticleOneByArticleId(articleId,false);
    }

    @Override
    public ResultJson findBusinessArticles(PageParams pageParams) {
        IPage<Article> iPage = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        IPage<Article> page1 = articleDao.findBusinessArticles(iPage);
        Integer pages = (int) page1.getPages();
        if(pages < pageParams.getPage()){
            return ResultJson.success(null);
        }
        List<Article> articles = page1.getRecords();
        List<ArticleVo> articleVoList = copyList(articles, true,true);
        ArticlePagesVo articlePagesVo = new ArticlePagesVo();
        articlePagesVo.setArticles(articleVoList);
        articlePagesVo.setPages(pages);
        return ResultJson.success(articlePagesVo);
    }

    private Integer getStatus(String articleId, String userId) {
        //先从redis中获取
        Integer status = redisLikeService.getStatus(articleId,userId);
        if(status == null || status != -1){
            return status;
        }
        //若redis中没有数据，则从mysql中获取
        status = articleLikeService.getStatus(articleId,userId);
        return status;
    }


    private List<ArticleVo> copyList(List<Article> articles,boolean isUser,boolean isStatus) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        articles.forEach(article -> articleVoList.add(copy(article,isUser,isStatus)));
        return articleVoList;
    }

    private ArticleVo copy(Article article,boolean isUser,boolean isStatus) {
        ArticleVo articleVo = new ArticleVo();
        String userId = article.getUserId();
        String articleId = article.getArticleId();
        String categoryId = article.getCategoryId();
        Integer status = 0;
        Integer likeCounts = 0;
        String label = "";

        if(isUser){
            ArticleUserInfoVo articleUserInfoVo = userService.findArticleUserInfoByUserId(userId);

            if (articleUserInfoVo.getIsShop()==1){
                label = shopService.getUserLabelByUserId(userId);
            }
            BeanUtils.copyProperties(articleUserInfoVo,articleVo);
        }

        if(isStatus){
            status = getStatus(articleId,ThreadLocalUtil.getThread());
        }

        //先从redis中查询文章的点赞数
        likeCounts = redisLikeService.getLikeCountsByRedis(articleId);

        if(likeCounts != null){
            article.setLikeCounts(likeCounts);
        }

        ArticleBody articleBody = articleBodyService.findArticleContentByArticleId(articleId);

        List<Tag> tags = articleTagService.findArticleTagsByArticleId(articleId);

        Category category = categoryService.findArticleCategoryByArticleId(categoryId);

        BeanUtils.copyProperties(article,articleVo);
        articleVo.setStatus(status);
        articleVo.setLabel(label);
        articleVo.setContent(articleBody.getContent());
        articleVo.setContentHtml(articleBody.getContentHtml());
        articleVo.setTags(tags);
        articleVo.setCategory(category);
        return articleVo;
    }
}
