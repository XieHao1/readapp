package com.xh.readapp.vo.articles;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ArticlePageParams implements Serializable {
    private String articleId;
    private String userId;
    private String title;
    private String categoryId;
    private ArticleBodyParams body;
    private List<ArticleTagVo> tags;
    private String audioUrl;
}
