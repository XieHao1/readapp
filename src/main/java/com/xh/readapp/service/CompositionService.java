package com.xh.readapp.service;


import com.xh.readapp.util.ResultJson;

public interface CompositionService {
    /**
     * 获取分类后的真题
     * @param topicId 分类列表id
     * @return 分类后的真题
     */
    ResultJson getComposition(Integer topicId);

    /**
     * 获取真题详情
     * @param compositionId 真题id
     * @return 真题详情
     */
    ResultJson getCompositionDetails(Integer compositionId);
}
