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
     * @return 关注列表信息
     */
    ResultJson getAttentionData();
}
