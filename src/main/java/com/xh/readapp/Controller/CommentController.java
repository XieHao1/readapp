package com.xh.readapp.Controller;

import com.xh.readapp.common.Cache;
import com.xh.readapp.common.NoticeUpdateCache;
import com.xh.readapp.service.CommentService;
import com.xh.readapp.util.ResultJson;
import com.xh.readapp.vo.comment.CommentParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/article")
    @Cache(expire = 60*60*1000,name = "评论")
    public ResultJson findArticleComments(String articleId){
        return commentService.findArticleCommentsByArticleId(articleId);
    }

    @PostMapping("/create/change")
    @NoticeUpdateCache(name = "更新评论缓存",routingKey = "comment")
    public ResultJson comment(@RequestBody CommentParams commentParams){
        return commentService.insertComment(commentParams);
    }
}
