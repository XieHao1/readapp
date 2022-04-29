package com.xh.readapp.Controller;

import com.xh.readapp.common.Cache;
import com.xh.readapp.service.TopicService;
import com.xh.readapp.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Resource
    private TopicService topicService;

    @GetMapping("/getTopic")
    @Cache(expire = 24*60*60*1000,name = "获取真题练习列表")
    public ResultJson getTopic(){
        return topicService.getAllTopic();
    }
}
