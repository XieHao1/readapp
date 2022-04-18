package com.xh.readapp.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xh.readapp.dao.ArticleTagDao;
import com.xh.readapp.domain.ArticleTag;
import com.xh.readapp.domain.Tag;
import com.xh.readapp.service.ArticleTagService;
import com.xh.readapp.service.TagService;
import com.xh.readapp.util.UUIDUtil;
import com.xh.readapp.vo.articles.ArticleTagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleTagServiceImpl implements ArticleTagService {

    @Autowired
    private ArticleTagDao articleTagDao;

    @Autowired
    private TagService tagService;

    @Override
    public List<Tag> findArticleTagsByArticleId(String articleId) {
        LambdaQueryWrapper<ArticleTag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArticleTag::getArticleId,articleId);
        lambdaQueryWrapper.select(ArticleTag::getTagId);
        List<ArticleTag> articleTags = articleTagDao.selectList(lambdaQueryWrapper);
        if(articleTags==null){
            return null;
        }
        List<String> tagId = new ArrayList<>();
        articleTags.forEach(articleTag -> tagId.add(articleTag.getTagId()));
        return tagService.findTagsByTagId(tagId);
    }

    @Override
    public void insertArticleTag(String articleId, List<ArticleTagVo> tags) {
        tags.forEach(tag ->{
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(articleId);
            articleTag.setTagId(tag.getTagId());
            articleTag.setId(UUIDUtil.getUUID());
            articleTagDao.insert(articleTag);
        });
    }

    @Override
    public int updateArticleTagsByArticleId(String articleId, List<ArticleTagVo> tags) {

        //查询id
        LambdaQueryWrapper<ArticleTag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArticleTag::getArticleId,articleId);
        lambdaQueryWrapper.select(ArticleTag::getId,ArticleTag::getTagId);
        List<ArticleTag> articleTags = articleTagDao.selectList(lambdaQueryWrapper);

        //如果集合中没有数据，则添加
        if(articleTags.size()==0){
            this.insertArticleTag(articleId,tags);
            return 1;
        }

        //如果集合中有数据,修改文章的标签
        //将所有的标签删除
        if(tags == null){
            this.delete(articleId);
            return 1;
        }

        //更换标签
        List<String> oldTagId = new ArrayList<>();
        List<String> newTagId = new ArrayList<>();
        articleTags.forEach(articleTag -> oldTagId.add(articleTag.getTagId()));
        tags.forEach(tag-> newTagId.add(tag.getTagId()));
        //如果两个集合相同，则不进行
        boolean equals = oldTagId.stream().sorted().collect(Collectors.joining())
                .equals(newTagId.stream().sorted().collect(Collectors.joining()));
        if(equals){
            return 1;
        }
        //先将原先的标签删除，在进行添加
        this.delete(articleId);
        this.insertArticleTag(articleId,tags);
        return 1;
    }

    private void delete(String articleId){
        LambdaQueryWrapper<ArticleTag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArticleTag::getArticleId,articleId);
        articleTagDao.delete(lambdaQueryWrapper);
    }
}
