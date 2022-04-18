package com.xh.readapp.service;

import com.xh.readapp.util.ResultJson;
import com.xh.readapp.vo.WeiXin.UserVo;
import com.xh.readapp.vo.articles.ArticleUserInfoVo;
import com.xh.readapp.vo.comment.CommentUserVo;
import com.xh.readapp.vo.WeiXin.LoginVo;
import com.xh.readapp.vo.user.UpdateUserVo;

import java.util.List;

public interface UserService {
    /**
     * 生成token
     * @param loginVo 微信传递的参数
     *@return token
     */
    ResultJson getToken(LoginVo loginVo);

    /**
     * 验证token并且返回用户数据
     * @param token token
     * @return 要返回的用户的数据
     */
    ResultJson getUserData(String token);

    /**
     * 通过文章表中的userID查询User的头像，名称，是否为商家等信息
     * @param userId 用户id
     * @return User的头像，名称，是否为商家的vo对象
     */
    ArticleUserInfoVo findArticleUserInfoByUserId(String userId);

    /**
     * 退出登录
     * @param token token令牌
     * @return null
     */
    ResultJson logout(String token);

    /**
     * 查询评论中用户的相关信息
     * @param userId 评论的id
     * @return 评论用户的相关信息
     */
    CommentUserVo findCommentUserById(String userId);

    /**
     * 通过用户id查询用户的相关信息
     * @param userId 用户的id
     * @return 用户的信息
     */
    ResultJson findUserDataByUserId(String userId);

    /**
     * 更改用户数据
     * @param updateUserVo 要更改用户的数据
     * @return 更新后的数据
     */
    ResultJson updateUserInfo(UpdateUserVo updateUserVo);

    /**
     * 通过用户的id查询用户所在城市
     * @param userId 用户的id
     * @return 用户的城市
     */
    String findUserCityByUserId(String userId);

    /**
     * 更新用户的关注数
     * @param userId 用户的id
     * @param attentionCounts 用户的的关注数
     */
    void updateUserAttentionCounts(String userId,Integer attentionCounts);

    /**
     *
     * 更新被关注用户的粉丝数
     * @param toUserId 被关注用户的id
     * @param fansCounts 被关注用户的粉丝数
     */
    void updateUserFansCounts(String toUserId, Integer fansCounts);

    /**
     * 获取关注用户的相关信息
     * @param list 关注用户的id集合
     * @return 关注用户的相关信息
     */
    List<UserVo> findAttentionData(List<String> list);
}
