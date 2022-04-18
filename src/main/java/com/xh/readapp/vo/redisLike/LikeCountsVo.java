package com.xh.readapp.vo.redisLike;

import lombok.Data;

import java.io.Serializable;

@Data
public class LikeCountsVo implements Serializable {
    private String articleId;
    private Integer likeCounts;
}
