package com.xh.readapp.service.serviceImpl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xh.readapp.dao.UserDao;
import com.xh.readapp.dictionary.ErrorEnum;
import com.xh.readapp.domain.User;
import com.xh.readapp.service.ArticleService;
import com.xh.readapp.service.ShopService;
import com.xh.readapp.service.UserService;
import com.xh.readapp.util.*;
import com.xh.readapp.vo.WeiXin.UserVo;
import com.xh.readapp.vo.WeiXin.WeiXinInfo;
import com.xh.readapp.vo.WeiXin.WeiXinUserVo;
import com.xh.readapp.vo.articles.ArticleUserInfoVo;
import com.xh.readapp.vo.comment.CommentUserVo;
import com.xh.readapp.vo.WeiXin.LoginVo;
import com.xh.readapp.vo.user.UpdateUserVo;
import com.xh.readapp.vo.user.UserDataVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class UserServiceImpl implements UserService {

    private final static String REDIS_TOKEN_KEY_PREFIX = "token_";

    @Value("${weixin.AppID}")
    private String AppID;

    @Value("${weixin.AppSecret}")
    private String AppSecret;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShopService shopService;

    @Autowired
    @Lazy
    private ArticleService articleService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public ResultJson getToken(LoginVo loginVo) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
        String replaceUrl = url.replace("{0}", AppID).replace("{1}", AppSecret).replace("{2}", loginVo.getCode());
        String weiXinJson = HttpUtil.get(replaceUrl);
        WeiXinInfo weiXinInfo = JSON.parseObject(weiXinJson, WeiXinInfo.class);
        //校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String openId = weiXinInfo.getOpenid();
        String signature2 = DigestUtils.sha1Hex(loginVo.getRawData() + weiXinInfo.getSession_key());
        if(!loginVo.getSignature().equals(signature2)){
            return ResultJson.fail(ErrorEnum.SIGNATURE_ERROR.getCode(), ErrorEnum.SIGNATURE_ERROR.getMsg());
        }
        WeiXinUserVo weiXinUserVo = JSON.parseObject(loginVo.getRawData(), WeiXinUserVo.class);
        weiXinUserVo.setOpenId(openId);
        //判断数据库中是否有该用户的信息，若没有，则存入数据库中
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserId,openId);
        User user = userDao.selectOne(lambdaQueryWrapper);
        if(user == null){
            userDao.insert(convert(weiXinUserVo));
            user = userDao.selectOne(lambdaQueryWrapper);
        }
        //若有，则生成token,并且保存在redis中
        String token = new JWTUtil().getToken(openId);
        redisTemplate.opsForValue().set(REDIS_TOKEN_KEY_PREFIX+token,JSON.toJSONString(user),7,TimeUnit.DAYS);
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        return ResultJson.success(map);
    }

    @Override
    public ResultJson getUserData(String token) {
        String rawData = redisTemplate.opsForValue().get(REDIS_TOKEN_KEY_PREFIX + token);
        UserVo userVo = JSON.parseObject(rawData, UserVo.class);
        //更新用户最后登录时间
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserId,ThreadLocalUtil.getThread());
        User user = new User();
        String lastTime = TimeUtil.getStringTime();
        user.setLastLogin(lastTime);
        userDao.update(user, lambdaQueryWrapper);
        return ResultJson.success(userVo);
    }

    @Override
    public ArticleUserInfoVo findArticleUserInfoByUserId(String userId) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserId,userId);
        lambdaQueryWrapper.select(User::getNickname,User::getAvatarUrl,User::getIsShop);
        User user = userDao.selectOne(lambdaQueryWrapper);
        ArticleUserInfoVo articleUserInfoVo = new ArticleUserInfoVo();
        BeanUtils.copyProperties(user,articleUserInfoVo);
        return articleUserInfoVo;
    }

    @Override
    public ResultJson logout(String token) {
        //从redis中将缓存删除
        redisTemplate.delete(REDIS_TOKEN_KEY_PREFIX+token);
        //将本地线程中的数据删除
        ThreadLocalUtil.removeThread();
        return ResultJson.success(null);
    }

    @Override
    public CommentUserVo findCommentUserById(String userId) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserId,userId);
        lambdaQueryWrapper.select(User::getUserId,User::getNickname,User::getAvatarUrl);
        User user = userDao.selectOne(lambdaQueryWrapper);
        CommentUserVo commentUserVo = new CommentUserVo();
        BeanUtils.copyProperties(user,commentUserVo);
        return commentUserVo;
    }

    @Override
    public ResultJson findUserDataByUserId(String userId) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserId,userId);
        User user = userDao.selectOne(lambdaQueryWrapper);
        return ResultJson.success(copy(user));
    }

    @Override
    @Transactional
    public ResultJson updateUserInfo(UpdateUserVo updateUserVo) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserId,updateUserVo.getUserId());
        User user = new User();
        BeanUtils.copyProperties(updateUserVo,user);
        userDao.update(user, lambdaQueryWrapper);
        user = userDao.selectOne(lambdaQueryWrapper);
        return ResultJson.success(copy(user));
    }

    @Override
    public String findUserCityByUserId(String userId) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserId,userId);
        lambdaQueryWrapper.select(User::getProvince);
        return userDao.selectOne(lambdaQueryWrapper).getProvince();
    }

    @Override
    public void updateUserAttentionCounts(String userId,Integer attentionCounts) {
        User user = new User();
        user.setAttentionCounts(attentionCounts);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserId,userId);
        userDao.update(user,lambdaQueryWrapper);
    }

    @Override
    public void updateUserFansCounts(String toUserId, Integer fansCounts) {
        User user = new User();
        user.setFansCounts(fansCounts);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserId,toUserId);
        userDao.update(user,lambdaQueryWrapper);
    }

    @Override
    public List<UserVo> findAttentionData(List<String> list) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(list.size() == 0){
            return null;
        }
        lambdaQueryWrapper.in(User::getUserId,list);
        List<User> users = userDao.selectList(lambdaQueryWrapper);
        List<UserVo> userVoList = new ArrayList<>();
        users.forEach(user -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user,userVo);
            userVoList.add(userVo);
        });
        return userVoList;
    }


    private UserDataVo copy(User user){

        if(user == null){
            return null;
        }

        String label = "";
        if(1==user.getIsShop()){
            label = shopService.getUserLabelByUserId(user.getUserId());
        }
        Integer likeCounts = articleService.countLikeCountsByUserId(user.getUserId());
        UserDataVo userDataVo = new UserDataVo();
        BeanUtils.copyProperties(user,userDataVo);
        userDataVo.setLikeCounts(likeCounts);
        userDataVo.setLabel(label);
        return userDataVo;
    }

    private User convert(WeiXinUserVo weiXinUserVo){
        User registerUser = new User();
        BeanUtils.copyProperties(weiXinUserVo,registerUser);
        registerUser.setNickname(weiXinUserVo.getNickName());
        registerUser.setUserId(weiXinUserVo.getOpenId());
        registerUser.setCreateDate(TimeUtil.getStringTime());
        registerUser.setLastLogin(TimeUtil.getStringTime());
        return registerUser;
    }
}
