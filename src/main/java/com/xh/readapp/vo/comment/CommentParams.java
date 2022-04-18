package com.xh.readapp.vo.comment;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentParams implements Serializable {
    private String articleId;
    private String content;
    private String parent;
    private String toUserId;
    private String userId;
}
