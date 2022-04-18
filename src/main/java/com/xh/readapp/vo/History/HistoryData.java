package com.xh.readapp.vo.History;

import lombok.Data;

import java.io.Serializable;

@Data
public class HistoryData implements Serializable {
    private String time;
    private String timeId;
    private String readingTime;
    private String bookmark;
}
