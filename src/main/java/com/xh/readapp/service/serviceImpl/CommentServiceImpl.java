package com.xh.readapp.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xh.readapp.dao.CommentDao;
import com.xh.readapp.dictionary.ErrorEnum;
import com.xh.readapp.domain.Comment;
import com.xh.readapp.service.CommentService;
import com.xh.readapp.service.ThreadService;
import com.xh.readapp.service.UserService;
import com.xh.readapp.util.ResultJson;
import com.xh.readapp.util.ThreadLocalUtil;
import com.xh.readapp.util.TimeUtil;
import com.xh.readapp.util.UUIDUtil;
import com.xh.readapp.vo.comment.CommentParams;
import com.xh.readapp.vo.comment.CommentUserVo;
import com.xh.readapp.vo.comment.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ThreadService threadService;

    @Override
    public ResultJson findArticleCommentsByArticleId(String articleId) {
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getArticleId,articleId);
        lambdaQueryWrapper.eq(Comment::getLevel,"1");
        List<Comment> comments = commentDao.selectList(lambdaQueryWrapper);
        return ResultJson.success(copyList(comments));
    }

    @Override
    @Transactional
    public ResultJson insertComment(CommentParams commentParams) {
        String articleId = commentParams.getArticleId();
        String parent = commentParams.getParent();
        String toUserId = commentParams.getToUserId();
        String content = commentParams.getContent();
        Comment comment = new Comment();
        comment.setId(UUIDUtil.getUUID());
        comment.setContent(content);
        comment.setCreateDate(TimeUtil.getStringTime());
        comment.setArticleId(articleId);
        comment.setUserId(commentParams.getUserId());
        if(parent == null || "0".equals(parent)){
            comment.setLevel("1");
        }else {
            comment.setLevel("2");
        }
        comment.setParentId(parent==null ? "0" : parent);
        comment.setToUid(toUserId==null ? "0" : toUserId);
        int insert = commentDao.insert(comment);
        //更新文章表中的评论数
        threadService.updateArticleCommentCounts(comment);
        if (insert != 1){
            return ResultJson.fail(ErrorEnum.INSERT_COMMENT_ERROR.getCode(),ErrorEnum.INSERT_HISTORY_ERROR.getMsg());
        }
        return ResultJson.success(null);
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVoList = new ArrayList<>();
        comments.forEach(comment -> commentVoList.add(copy(comment)));
        return commentVoList;
    }

    private CommentVo copy(Comment comment) {

        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);

        //查询作者信息
        CommentUserVo commentUserVo = userService.findCommentUserById(comment.getUserId());
        commentVo.setUser(commentUserVo);

        String level = comment.getLevel();
        //子评论 level == 1 时有子评论,通过parentID查询子评论
        //level == 2 没有子评论
        if("1".equals(level)){
            //通过查找和id相同的parent_id来查询子评论
            String id = comment.getId();
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            commentVo.setChildren(commentVoList);
        }
        if("2".equals(level)){
            //查询子评论是给谁评论的
            String toUid = comment.getToUid();
            CommentUserVo commentToUser = userService.findCommentUserById(toUid);
            commentVo.setToUser(commentToUser);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(String id) {
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getParentId,id);
        lambdaQueryWrapper.eq(Comment::getLevel,"2");
        List<Comment> comments = commentDao.selectList(lambdaQueryWrapper);
        return copyList(comments);
    }

}
