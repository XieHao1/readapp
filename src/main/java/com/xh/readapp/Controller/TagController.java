package com.xh.readapp.Controller;

import com.xh.readapp.common.Cache;
import com.xh.readapp.service.TagService;
import com.xh.readapp.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping()
    @Cache(expire = 60*60*1000,name = "查找所有标签")
    public ResultJson findAllTags(){
        return tagService.findAllTags();
    }

    @GetMapping("/detail")
    @Cache(expire = 60*60*1000,name = "查找指定标签")
    public ResultJson detail(String tagId){
        return tagService.findTagById(tagId);
    }

    @PostMapping("/specify")
    @Cache(expire = 60*60*1000,name="查找指定类别的标签")
    public ResultJson specifyTags(String categoryId){
        return tagService.findTagsByCategoryId(categoryId);
    }
}
