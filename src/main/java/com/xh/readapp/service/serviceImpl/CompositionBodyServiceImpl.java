package com.xh.readapp.service.serviceImpl;

import com.xh.readapp.dao.CompositionBodyDao;
import com.xh.readapp.domain.CompositionBody;
import com.xh.readapp.service.CompositionBodyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CompositionBodyServiceImpl implements CompositionBodyService {

    @Resource
    private CompositionBodyDao compositionBodyDao;

    @Override
    public CompositionBody getCompositionDetails(Integer compositionId) {
        return compositionBodyDao.findCompositionBodyDaoByCompositionId(compositionId);
    }
}
