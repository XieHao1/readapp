package com.xh.readapp.Controller;

import com.xh.readapp.service.ShopService;
import com.xh.readapp.service.UserService;
import com.xh.readapp.util.ResultJson;
import com.xh.readapp.vo.WeiXin.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @PostMapping("/authLogin")
    public ResultJson login(@RequestBody LoginVo loginVo){
        return userService.getToken(loginVo);
    }

    @GetMapping("/currentUser")
    public ResultJson userData(@RequestHeader("Authorization") String token){
        return userService.getUserData(token);
    }

    @PostMapping("/shop")
    public ResultJson shopLabel(String userId){
        return shopService.getLabel(userId);
    }

    @GetMapping("/logout")
    public ResultJson logout(@RequestHeader("Authorization") String token){
        return userService.logout(token);
    }
}
