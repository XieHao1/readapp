package com.xh.readapp.Controller;

import com.xh.readapp.common.Cache;
import com.xh.readapp.common.NoticeUpdateCache;
import com.xh.readapp.service.FocusService;
import com.xh.readapp.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FocusController {

    @Autowired
    private FocusService focusService;

    @PostMapping("/focus")
    @NoticeUpdateCache(name = "更新个人资料",routingKey = "data")
    public ResultJson focus(String toUserId){
        return focusService.insertFocus(toUserId);
    }

    @PostMapping("/unFocus")
    @NoticeUpdateCache(name = "更新个人资料",routingKey = "data")
    public ResultJson unFocus(String toUserId){
        return focusService.deleteFocus(toUserId);
    }

    @PostMapping("/getAttention")
    @Cache(name = "个人资料关注列表")
    public ResultJson getAttention(String userId){
        return focusService.getAttentionData(userId);
    }

    @PostMapping("/getFocus")
    @Cache(name = "个人资料粉丝列表")
    public ResultJson getFocus(String userId){
        return focusService.getFocus(userId);
    }
}
