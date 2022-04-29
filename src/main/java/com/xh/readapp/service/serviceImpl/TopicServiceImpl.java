package com.xh.readapp.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xh.readapp.dao.TopicDao;
import com.xh.readapp.domain.Topic;
import com.xh.readapp.service.TopicService;
import com.xh.readapp.util.ResultJson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Resource
    private TopicDao topicDao;

    @Override
    public ResultJson getAllTopic() {
        LambdaQueryWrapper<Topic> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(Topic::getTopicId,Topic::getTopicName);
        List<Topic> topics = topicDao.selectList(lambdaQueryWrapper);
        return ResultJson.success(topics);
    }
}
