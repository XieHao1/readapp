package com.xh.readapp.service;

import com.xh.readapp.util.ResultJson;

public interface ShopService {
    /**
     *获取商家的标签
     * @param userId 用户是商家的id
     * @return 商家的标签
     */
    ResultJson getLabel(String userId);

    /**
     * 通过文章表中的userId查询商家的标签
     * @param userId 用户的id
     * @return 商家的标签
     */
    String getUserLabelByUserId(String userId);
}
