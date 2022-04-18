package com.xh.readapp.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xh.readapp.dictionary.ErrorEnum;
import com.xh.readapp.domain.User;
import com.xh.readapp.util.JWTUtil;
import com.xh.readapp.util.ResultJson;
import com.xh.readapp.util.ThreadLocalUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        String token = request.getHeader("Authorization");

        if(StringUtils.isBlank(token)){
            resultJSON(response);
            return false;
        }

        //从redis中获取用户数据，若可以获取，则可以登录
        String rawData = redisTemplate.opsForValue().get("token_" + token);

        if(StringUtils.isBlank(rawData)){
            resultJSON(response);
            return false;
        }
        //验证token的合法性
        DecodedJWT verify = new JWTUtil().verify(token);
        //将用户的id放入线程池中
        ThreadLocalUtil.putThread(JSON.parseObject(rawData,User.class).getUserId());
        return true;
    }

    private void resultJSON(HttpServletResponse response) throws IOException {
        ResultJson fail = ResultJson.fail(ErrorEnum.LOGIN_ERROR.getCode(), ErrorEnum.LOGIN_ERROR.getMsg());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(JSON.toJSONString(fail));
    }
}
