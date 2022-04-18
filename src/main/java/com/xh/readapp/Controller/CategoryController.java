package com.xh.readapp.Controller;

import com.xh.readapp.common.Cache;
import com.xh.readapp.service.CategoryService;
import com.xh.readapp.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

   @Autowired
   private CategoryService categoryService;

    @GetMapping()
    @Cache(expire = 60*60*1000,name = "查找所有类别")
    public ResultJson findAllCategories(){
        return categoryService.findAllCategories();
    }

    @GetMapping("/detail")
    public ResultJson detail(String categoryId){
        return categoryService.findCategoryById(categoryId);
    }
}
