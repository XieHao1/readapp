package com.xh.readapp.vo.articles;

import com.xh.readapp.domain.Category;
import com.xh.readapp.domain.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ArticleVo implements Serializable {
    private String userId;
    private String articleId;
    private String title;
    private Integer viewCounts;
    private Integer commentCounts;
    private Integer likeCounts;
    private String createDate;

    private String nickname;
    private String avatarUrl;

    private String label;

    private String content;

    private List<Tag> tags;

    private Category category;

    //文章的点赞状态
    private Integer status;

    private String audioUrl;

}
