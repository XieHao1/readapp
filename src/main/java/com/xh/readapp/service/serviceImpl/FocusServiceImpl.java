package com.xh.readapp.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xh.readapp.dao.FocusDao;
import com.xh.readapp.dictionary.ErrorEnum;
import com.xh.readapp.domain.Focus;
import com.xh.readapp.service.FocusService;
import com.xh.readapp.service.ThreadService;
import com.xh.readapp.service.UpdateCacheService;
import com.xh.readapp.service.UserService;
import com.xh.readapp.util.ResultJson;
import com.xh.readapp.util.ThreadLocalUtil;
import com.xh.readapp.util.TimeUtil;
import com.xh.readapp.util.UUIDUtil;
import com.xh.readapp.vo.WeiXin.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FocusServiceImpl implements FocusService {

    @Autowired
    private FocusDao focusDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ThreadService threadService;

    @Autowired
    private UpdateCacheService updateCacheService;

    @Override
    @Transactional
    public ResultJson insertFocus(String toUserId) {
        String userId = ThreadLocalUtil.getThread();
        //先判断是否关注
        boolean focus1 = isFocus(userId, toUserId);
        if(focus1){
            return ResultJson.success("已关注");
        }
        Focus focus = new Focus();
        focus.setFocusId(UUIDUtil.getUUID());
        focus.setUserId(userId);
        focus.setToUserId(toUserId);
        focus.setFocusTime(TimeUtil.getStringTime());
        int insert = focusDao.insert(focus);
        if (insert != 1){
            return ResultJson.fail(ErrorEnum.INSERT_FOCUS_ERROR.getCode(), ErrorEnum.INSERT_FOCUS_ERROR.getMsg());
        }
        //在另一个线程中更新用户的关注数和粉丝数
        threadService.updateUserFocus(userId,toUserId);
        //删除文章缓存
        updateCacheService.deleteArticleCache();
        return ResultJson.success(null);
    }

    @Override
    @Transactional
    public ResultJson deleteFocus(String toUserId) {
        String userId = ThreadLocalUtil.getThread();
        //先判段是否关注
        boolean focus = isFocus(userId, toUserId);
        if(!focus){
            return ResultJson.fail(ErrorEnum.DELETE_FOCUS_ERROR.getCode(), ErrorEnum.DELETE_FOCUS_ERROR.getMsg()+",还未关注该用户");
        }
        LambdaQueryWrapper<Focus> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Focus::getToUserId,toUserId);
        lambdaQueryWrapper.eq(Focus::getUserId,userId);
        int delete = focusDao.delete(lambdaQueryWrapper);
        if (delete != 1){
            return ResultJson.fail(ErrorEnum.DELETE_FOCUS_ERROR.getCode(), ErrorEnum.DELETE_FOCUS_ERROR.getMsg());
        }
        //在另一个线程中更新用户的关注数和粉丝数
        threadService.updateUserFocus(userId,toUserId);
        //删除文章缓存
        updateCacheService.deleteArticleCache();
        return ResultJson.success(null);
    }

    @Override
    @Transactional
    public ResultJson getAttentionData(String userId) {
        LambdaQueryWrapper<Focus> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Focus::getUserId,userId);
        List<Focus> foci = focusDao.selectList(lambdaQueryWrapper);
        List<String> list = new ArrayList<>();
        foci.forEach(focus -> list.add(focus.getToUserId()));
        List<UserVo> userVoList = userService.findAttentionData(list);
        return ResultJson.success(userVoList);
    }

    @Override
    public Integer getIsFocus(String myUserId,String toUSerId) {
        boolean focus = isFocus(myUserId, toUSerId);
        return focus ? 1 : 0;
    }

    private boolean isFocus(String userId,String toUserId){
        LambdaQueryWrapper<Focus> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Focus::getUserId,userId);
        lambdaQueryWrapper.eq(Focus::getToUserId,toUserId);
        int i = focusDao.selectCount(lambdaQueryWrapper).intValue();
        return i >= 1;
    }

}
