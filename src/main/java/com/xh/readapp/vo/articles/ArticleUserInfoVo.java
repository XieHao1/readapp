package com.xh.readapp.vo.articles;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleUserInfoVo implements Serializable {
    private String nickname;
    private String avatarUrl;
    private Integer isShop;
}
