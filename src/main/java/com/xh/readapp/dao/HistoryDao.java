package com.xh.readapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xh.readapp.domain.History;
import com.xh.readapp.vo.History.*;

import java.util.List;

public interface HistoryDao extends BaseMapper<History> {

    List<HistoryInfoVo> findInfoByTimeId(String timeId);

    /**
     * 一年中阅读的天数
     * @return 哪年阅读的天数
     */
    List<YearStatistics> yearStatistics(String userId);

    /**
     * 一个月中阅读的天数
     * @return 哪年哪月阅读的天数
     */
    List<MonthStatistics> monthStatistics(String userId);

    /**
     * 阅读时间排行榜
     * @return 返回用户的相关数据和总共的阅读时间
     */
    List<LeaderboardVo> leaderboard();

    /**
     * 通过用户id查询用户的历史时间
     * @param userId 用户的id
     * @return 用户的历史记录消息
     */
    List<HistoryData> findAllTimeByUserId(String userId);

    /**
     * 通过用户的所在地查询排名
     * @param province 用户所在的城市
     * @return 用户的排名情况
     */
    List<LeaderboardVo> regionalLeaderboard(String province);

    /**
     * 获取用户总共的阅读时间
     * @param userId 用户的id
     * @return 总共的阅读时间
     */
    String findAllReadingTimeByUserId(String userId);
}
