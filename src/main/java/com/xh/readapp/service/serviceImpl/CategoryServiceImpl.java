package com.xh.readapp.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xh.readapp.dao.CategoryDao;
import com.xh.readapp.domain.Category;
import com.xh.readapp.service.CategoryService;
import com.xh.readapp.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Category findArticleCategoryByArticleId(String categoryId) {
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Category::getCategoryId,categoryId);
        return categoryDao.selectOne(lambdaQueryWrapper);
    }

    @Override
    public ResultJson findAllCategories() {
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(Category::getCategoryId,Category::getAvatar,Category::getCategoryName);
        List<Category> categories = categoryDao.selectList(lambdaQueryWrapper);
        return ResultJson.success(categories);
    }

    @Override
    public ResultJson findCategoryById(String categoryId) {
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Category::getCategoryId,categoryId);
        Category category = categoryDao.selectOne(lambdaQueryWrapper);
        return ResultJson.success(category);
    }
}
