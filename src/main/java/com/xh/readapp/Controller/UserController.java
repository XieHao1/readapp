package com.xh.readapp.Controller;

import com.xh.readapp.common.Cache;
import com.xh.readapp.common.NoticeUpdateCache;
import com.xh.readapp.service.UserService;
import com.xh.readapp.util.ResultJson;
import com.xh.readapp.vo.user.UpdateUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/data")
    @Cache(expire = 60*60*1000,name = "个人资料")
    public ResultJson data(String userId){
        return userService.findUserDataByUserId(userId);
    }

    @PostMapping("/update")
    @NoticeUpdateCache(name = "更新个人资料",routingKey = "data")
    public ResultJson update(@RequestBody UpdateUserVo updateUserVo){
        return userService.updateUserInfo(updateUserVo);
    }

}
