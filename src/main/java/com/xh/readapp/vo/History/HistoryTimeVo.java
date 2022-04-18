package com.xh.readapp.vo.History;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HistoryTimeVo implements Serializable {
    private String totalReadingTime;
    private List<HistoryData> historyData;
    private List<YearStatistics> yearStatistics;
    private List<MonthStatistics> monthStatistics;
}
