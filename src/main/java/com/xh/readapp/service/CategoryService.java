package com.xh.readapp.service;

import com.xh.readapp.domain.Category;
import com.xh.readapp.util.ResultJson;


public interface CategoryService {
    /**
     * 通过文章表的中的类别id查询文章的类别信息
     * @param categoryId 类别id
     * @return 文章的类别信息
     */
    Category findArticleCategoryByArticleId(String categoryId);

    /**
     * 查询所有文章的类别
     * @return 所有文章的类别信息
     */
    ResultJson findAllCategories();

    /**
     * 通过类别id查询类别的信息
     * @param categoryId 类别id
     * @return 类别对应的信息
     */
    ResultJson findCategoryById(String categoryId);
}
