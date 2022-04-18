package com.xh.readapp.vo.History;

import lombok.Data;

import java.io.Serializable;

@Data
public class MonthStatistics implements Serializable {
    private String year;
    private String month;
    private Integer count;
}
