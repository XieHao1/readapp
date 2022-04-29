package com.xh.readapp.service.serviceImpl;

import com.xh.readapp.dao.CompositionDao;
import com.xh.readapp.domain.Composition;
import com.xh.readapp.domain.CompositionBody;
import com.xh.readapp.service.CompositionBodyService;
import com.xh.readapp.service.CompositionService;
import com.xh.readapp.util.ResultJson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompositionServiceImpl implements CompositionService {

    @Resource
    private CompositionDao compositionDao;

    @Resource
    private CompositionBodyService compositionBodyService;

    @Override
    public ResultJson getComposition(Integer topicId) {
        List<Composition> compositions = compositionDao.getComposition(topicId);
        return ResultJson.success(compositions);
    }

    @Override
    public ResultJson getCompositionDetails(Integer compositionId) {
        CompositionBody compositionBody = compositionBodyService.getCompositionDetails(compositionId);
        return ResultJson.success(compositionBody);
    }
}
