package com.xh.readapp.service.serviceImpl;

import com.xh.readapp.dao.HistoryDao;
import com.xh.readapp.dictionary.ErrorEnum;
import com.xh.readapp.domain.History;
import com.xh.readapp.service.HistoryService;
import com.xh.readapp.service.UserService;
import com.xh.readapp.util.ResultJson;
import com.xh.readapp.util.UUIDUtil;
import com.xh.readapp.vo.History.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryDao historyDao;

    @Autowired
    private UserService userService;

    @Override
    public ResultJson findAllTimeByUserId(String userId) {
        //查询总阅读时间
        String totalReadingTime = historyDao.findAllReadingTimeByUserId(userId);
        //阅读信息
        List<HistoryData> historyData = historyDao.findAllTimeByUserId(userId);
        //年统计
        List<YearStatistics> yearStatistics = historyDao.yearStatistics(userId);
        //月统计
        List<MonthStatistics> monthStatistics = historyDao.monthStatistics(userId);

        HistoryTimeVo historyTimeVo = new HistoryTimeVo();
        historyTimeVo.setTotalReadingTime(totalReadingTime);
        historyTimeVo.setHistoryData(historyData);
        historyTimeVo.setYearStatistics(yearStatistics);
        historyTimeVo.setMonthStatistics(monthStatistics);

        return ResultJson.success(historyTimeVo);
    }

    @Override
    public ResultJson findInfoByTimeId(String timeId) {
        List<HistoryInfoVo> list = historyDao.findInfoByTimeId(timeId);
        return ResultJson.success(list);
    }

    @Override
    public ResultJson insertHistory(HistoryParams historyParams) {
        History history = new History();
        BeanUtils.copyProperties(historyParams,history);
        history.setTimeId(UUIDUtil.getUUID());
        int insert = historyDao.insert(history);
        if(insert==1){
            return ResultJson.success(null);
        }
        return ResultJson.fail(ErrorEnum.INSERT_HISTORY_ERROR.getCode(),ErrorEnum.INSERT_HISTORY_ERROR.getMsg());
    }

    @Override
    public ResultJson leaderboard() {
        List<LeaderboardVo> leaderboardVo = historyDao.leaderboard();
        return ResultJson.success(leaderboardVo);
    }

    @Override
    public ResultJson regionalLeaderboard(String userId) {
        String province = userService.findUserCityByUserId(userId);
        if(StringUtils.isBlank(province)){
            return ResultJson.success(null);
        }
        List<LeaderboardVo> leaderboardVo = historyDao.regionalLeaderboard(province);
        return ResultJson.success(leaderboardVo);
    }
}
