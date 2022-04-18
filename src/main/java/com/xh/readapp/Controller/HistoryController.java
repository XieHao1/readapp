package com.xh.readapp.Controller;

import com.xh.readapp.common.Cache;
import com.xh.readapp.common.NoticeUpdateCache;
import com.xh.readapp.service.HistoryService;
import com.xh.readapp.util.ResultJson;
import com.xh.readapp.vo.History.HistoryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @PostMapping()
    @Cache(expire = 60*60*1000,name = "阅读室")
    public ResultJson getHistoryTime(String userId){
        return historyService.findAllTimeByUserId(userId);
    }

    @PostMapping("/view")
    @Cache(expire = 60*60*1000,name = "阅读室内容")
    public ResultJson getHistoryInfo(String timeId){
        return historyService.findInfoByTimeId(timeId);
    }

    @PostMapping("/reading")
    @NoticeUpdateCache(name = "更新阅读室缓存",routingKey = "history")
    public ResultJson readInfo(@RequestBody HistoryParams historyParams){
        return historyService.insertHistory(historyParams);
    }

    @GetMapping("/leaderboard")
    @Cache(expire = 60*60*1000,name = "阅读室排行榜")
    public ResultJson leaderboard(){
        return historyService.leaderboard();
    }

    @PostMapping("/regionalLeaderboard")
    @Cache(expire = 60*60*1000,name = "阅读室地区排行榜")
    public ResultJson regionalLeaderboard(String userId){
        return historyService.regionalLeaderboard(userId);
    }
}
