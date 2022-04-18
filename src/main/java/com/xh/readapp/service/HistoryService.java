package com.xh.readapp.service;

import com.xh.readapp.util.ResultJson;
import com.xh.readapp.vo.History.HistoryParams;

public interface HistoryService {
    /**
     * 通过用户id查询用户在阅读室中的历史记录
     * @param userId 用户的id
     * @return 历史时间和历史时间id
     */
    ResultJson findAllTimeByUserId(String userId);

    /**
     * 通过时间的id查询历史记录信息
     * @param timeId 时间的id
     * @return 返回feel，abstract，school
     */
    ResultJson findInfoByTimeId(String timeId);

    /**
     * 将用户的历史记录存入数据库中
     * @param historyParams 用户历史相关数据
     * @return null
     */
    ResultJson insertHistory(HistoryParams historyParams);

    /**
     * 按照阅读的时间进行排名
     * @return 排名后的列表
     */
    ResultJson leaderboard();

    /**
     * 根据用户的地区进行排名
     * @param userId 用户的id
     * @return 排名后的列表
     */
    ResultJson regionalLeaderboard(String userId);
}
