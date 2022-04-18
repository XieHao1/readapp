package com.xh.readapp.vo.articles;

import lombok.Data;

@Data
public class SearchArticleParams {
    private Integer page;
    private Integer pageSize;
    private String article;
}
