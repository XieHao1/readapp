package com.xh.readapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xh.readapp.domain.Composition;

import java.util.List;

public interface CompositionDao extends BaseMapper<Composition> {
    /**
     * 查找分类后的作文
     * @param topicId 分类id
     * @return 分类后的作文
     */
    List<Composition> getComposition(Integer topicId);
}
