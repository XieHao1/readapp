package com.xh.readapp.vo.History;

import lombok.Data;

import java.io.Serializable;

@Data
public class HistoryParams implements Serializable {
    private String time;
    private String feel;
    private String excerpt;
    private String userId;
    private String readingTime;
    private String bookmark;
}
