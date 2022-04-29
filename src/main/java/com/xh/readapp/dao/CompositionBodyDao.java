package com.xh.readapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xh.readapp.domain.CompositionBody;

public interface CompositionBodyDao extends BaseMapper<CompositionBody> {
    /**
     * 搜索指定作文id的内容
     * @param compositionId 作文id
     * @return 内容
     */
    CompositionBody findCompositionBodyDaoByCompositionId(Integer compositionId);
}
