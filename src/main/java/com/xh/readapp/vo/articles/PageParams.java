package com.xh.readapp.vo.articles;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageParams implements Serializable {
    private Integer page;
    private Integer pageSize;
    private String categoryId;
    private String tagId;
    private String userId;
}
