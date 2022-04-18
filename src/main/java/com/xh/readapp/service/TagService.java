package com.xh.readapp.service;

import com.xh.readapp.domain.Tag;
import com.xh.readapp.util.ResultJson;

import java.util.List;

public interface TagService {
    /**
     * 通过标签的id查询标签的信息
     * @param tagId 一个文章中包含的所有标签id
     * @return 文章所有标签信息
     */
    List<Tag> findTagsByTagId(List<String> tagId);

    /**
     * 查询所有的文章标签
     * @return 所有的文章标签内容
     */
    ResultJson findAllTags();

    /**
     * 通过标签id查询标签的信息
     * @param tagId 标签id
     * @return 标签的信息
     */
    ResultJson findTagById(String tagId);

    /**
     * 通过类别的id查询对应的标签信息
     * @param categoryId 类别的id
     * @return 标签的信息
     */
    ResultJson findTagsByCategoryId(String categoryId);
}
