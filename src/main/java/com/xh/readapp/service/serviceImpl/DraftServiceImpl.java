package com.xh.readapp.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xh.readapp.dao.DraftDao;
import com.xh.readapp.domain.Draft;
import com.xh.readapp.service.DraftService;
import com.xh.readapp.util.ResultJson;
import com.xh.readapp.util.UUIDUtil;
import com.xh.readapp.vo.draft.DraftParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DraftServiceImpl implements DraftService {

    @Autowired
    private DraftDao draftDao;

    @Override
    public ResultJson insertDraft(DraftParams draftParams) {
        String userId = draftParams.getUserId();
        //先判断该用户是否有草稿，若有，则删除
        LambdaQueryWrapper<Draft> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Draft::getUserId,userId);
        Draft draft = draftDao.selectOne(lambdaQueryWrapper);
        if(draft != null){
            draftDao.delete(lambdaQueryWrapper);
        }
        //添加草稿
        String draftId  = UUIDUtil.getUUID();
        Draft insertDraft = new Draft();
        BeanUtils.copyProperties(draftParams,insertDraft);
        insertDraft.setDraftId(draftId);
        draftDao.insert(insertDraft);

        Map<String,String> map = new HashMap<>();
        map.put("draftId",draftId);
        return ResultJson.success(map);
    }

    @Override
    public ResultJson findDraftByUserId(String userId) {
        LambdaQueryWrapper<Draft> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Draft::getUserId,userId);
        Draft draft = draftDao.selectOne(lambdaQueryWrapper);
        if (draft == null){
            return ResultJson.success(null);
        }
        return ResultJson.success(draft);
    }
}
