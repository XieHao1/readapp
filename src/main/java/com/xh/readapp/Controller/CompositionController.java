package com.xh.readapp.Controller;

import com.xh.readapp.common.Cache;
import com.xh.readapp.service.CompositionService;
import com.xh.readapp.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/composition")
public class CompositionController {

    @Resource
    private CompositionService compositionService;

    @GetMapping("/getComposition")
    @Cache(expire = 24*60*60*1000,name = "查询分类后的真题列表")
    public ResultJson getComposition(Integer topicId){
        return compositionService.getComposition(topicId);
    }

    @PostMapping("/compositionDetails")
    @Cache(expire = 24*60*60*1000,name = "查询真题详情")
    public ResultJson compositionDetails(Integer compositionId){
        return compositionService.getCompositionDetails(compositionId);
    }
}
