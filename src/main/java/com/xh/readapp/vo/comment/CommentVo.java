package com.xh.readapp.vo.comment;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CommentVo implements Serializable {
    private String id;
    private String content;
    private String createDate;
    private String level;

    private CommentUserVo user;
    private CommentUserVo toUser;

    private List<CommentVo> children;
}
