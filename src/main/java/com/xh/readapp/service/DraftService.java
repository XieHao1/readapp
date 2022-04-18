package com.xh.readapp.service;


import com.xh.readapp.util.ResultJson;
import com.xh.readapp.vo.draft.DraftParams;

public interface DraftService {

    /**
     * 添加草稿
     * @param draftParams 草稿的参数
     * @return 草稿的id
     */
    ResultJson insertDraft(DraftParams draftParams);

    /**
     * 通过用户的id查询保存用户的草稿
     * @param userId 用户的id
     * @return 用户的草稿信息
     */
    ResultJson findDraftByUserId(String userId);
}
