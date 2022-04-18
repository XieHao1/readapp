package com.xh.readapp.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName comment
 */
public class Comment implements Serializable {
    /**
     * 32位UUID主键
     */
    private String id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创造时间
     */
    private String createDate;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 父评论id
     */
    private String parentId;

    /**
     * 给谁评论
     */
    private String toUid;

    /**
     * 评论等级 1为父评论，2为子评论
     */
    private String level;

    private static final long serialVersionUID = 1L;

    /**
     * 32位UUID主键
     */
    public String getId() {
        return id;
    }

    /**
     * 32位UUID主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 创造时间
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * 创造时间
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * 文章id
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * 文章id
     */
    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    /**
     * 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 父评论id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 父评论id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 给谁评论
     */
    public String getToUid() {
        return toUid;
    }

    /**
     * 给谁评论
     */
    public void setToUid(String toUid) {
        this.toUid = toUid;
    }

    /**
     * 评论等级 1为父评论，2为子评论
     */
    public String getLevel() {
        return level;
    }

    /**
     * 评论等级 1为父评论，2为子评论
     */
    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Comment other = (Comment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getToUid() == null ? other.getToUid() == null : this.getToUid().equals(other.getToUid()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getToUid() == null) ? 0 : getToUid().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", content=").append(content);
        sb.append(", createDate=").append(createDate);
        sb.append(", articleId=").append(articleId);
        sb.append(", userId=").append(userId);
        sb.append(", parentId=").append(parentId);
        sb.append(", toUid=").append(toUid);
        sb.append(", level=").append(level);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}