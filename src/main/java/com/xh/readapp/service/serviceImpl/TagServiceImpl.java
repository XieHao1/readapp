package com.xh.readapp.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xh.readapp.dao.TagDao;
import com.xh.readapp.domain.Tag;
import com.xh.readapp.service.TagService;
import com.xh.readapp.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public List<Tag> findTagsByTagId(List<String> tagId) {

        if (tagId.size()==0){
            return null;
        }

        LambdaQueryWrapper<Tag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Tag::getTagId,tagId);
        return tagDao.selectList(lambdaQueryWrapper);
    }

    @Override
    public ResultJson findAllTags() {
        LambdaQueryWrapper<Tag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Tag> tags = tagDao.selectList(lambdaQueryWrapper);
        return ResultJson.success(tags);
    }

    @Override
    public ResultJson findTagById(String tagId) {
        LambdaQueryWrapper<Tag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Tag::getTagId,tagId);
        Tag tag = tagDao.selectOne(lambdaQueryWrapper);
        return ResultJson.success(tag);
    }

    @Override
    public ResultJson findTagsByCategoryId(String categoryId) {
        LambdaQueryWrapper<Tag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Tag::getCategoryId,categoryId);
        List<Tag> tags = tagDao.selectList(lambdaQueryWrapper);
        return ResultJson.success(tags);
    }
}
