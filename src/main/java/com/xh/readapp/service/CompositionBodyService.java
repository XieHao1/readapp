package com.xh.readapp.service;


import com.xh.readapp.domain.CompositionBody;

public interface CompositionBodyService {
    /**
     * 获取作文详情
     * @param compositionId 作文的id
     * @return 作文详情
     */
    CompositionBody getCompositionDetails(Integer compositionId);
}
