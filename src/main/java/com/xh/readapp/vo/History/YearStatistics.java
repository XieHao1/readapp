package com.xh.readapp.vo.History;

import lombok.Data;

import java.io.Serializable;

@Data
public class YearStatistics implements Serializable {
    private String year;
    private Integer count;
}
