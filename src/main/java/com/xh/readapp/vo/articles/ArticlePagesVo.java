package com.xh.readapp.vo.articles;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ArticlePagesVo implements Serializable {
    Integer pages;
    List<ArticleVo> articles;
}
