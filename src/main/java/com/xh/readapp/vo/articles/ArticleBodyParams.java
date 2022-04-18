package com.xh.readapp.vo.articles;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleBodyParams implements Serializable {
    private String content;
    private String contentHtml;
}
