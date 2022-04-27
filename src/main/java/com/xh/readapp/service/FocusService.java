package com.xh.readapp.service;

import com.xh.readapp.util.ResultJson;

public interface FocusService {
    /**
     * 添加关注
     * @param toUserId 关注人的id
     * @return null
     */
    ResultJson insertFocus(String toUserId);

    /**
     * 取消关注
     * @param toUserId 取消关注人的id
     * @return null
     */
    ResultJson deleteFocus(String toUserId);

    /**
     * 获取关注列表
     * @param userId 用户的id
     * @return 关注列表信息
     */
    ResultJson getAttentionData(String userId);

    /**
     * 查看是是否关注
     * @param myUserId 自己的id
     * @param toUserId 关注的id
     * @return 0 表示未关注，1表示关注
     */
    Integer getIsFocus(String myUserId,String toUserId);

    /**
     * 获取用户的粉丝列表
     * @param userId 用户的id
     * @return 粉丝列表
     */
    ResultJson getFocus(String userId);
}
