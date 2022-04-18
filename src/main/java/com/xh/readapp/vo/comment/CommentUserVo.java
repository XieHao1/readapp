package com.xh.readapp.vo.comment;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentUserVo implements Serializable {
    private String userId;
    private String nickname;
    private String avatarUrl;
}
