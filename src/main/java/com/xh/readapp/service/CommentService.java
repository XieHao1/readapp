package com.xh.readapp.service;

import com.xh.readapp.util.ResultJson;
import com.xh.readapp.vo.comment.CommentParams;

public interface CommentService {
    /**
     * 通过文章的id查询文章的评论
     * @param articleId 文章id
     * @return 评论的内容
     */
    ResultJson findArticleCommentsByArticleId(String articleId);

    /**
     * 添加文章的评论
     * @param commentParams 文章评论的参数
     * @return null
     */
    ResultJson insertComment(CommentParams commentParams);
}
