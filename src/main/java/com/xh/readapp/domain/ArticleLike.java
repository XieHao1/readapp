package com.xh.readapp.domain;

import java.io.Serializable;

/**
 * 
 * @TableName article_like
 */
public class ArticleLike implements Serializable {
    /**
     * 文章点赞主键
     */
    private String articleLikeId;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 0表示未点赞，1表示点赞
     */
    private Integer status;

    /**
     * 用户id
     */
    private String userId;

    private static final long serialVersionUID = 1L;

    /**
     * 文章点赞主键
     */
    public String getArticleLikeId() {
        return articleLikeId;
    }

    /**
     * 文章点赞主键
     */
    public void setArticleLikeId(String articleLikeId) {
        this.articleLikeId = articleLikeId;
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
     * 0表示未点赞，1表示点赞
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 0表示未点赞，1表示点赞
     */
    public void setStatus(Integer status) {
        this.status = status;
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
        ArticleLike other = (ArticleLike) that;
        return (this.getArticleLikeId() == null ? other.getArticleLikeId() == null : this.getArticleLikeId().equals(other.getArticleLikeId()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticleLikeId() == null) ? 0 : getArticleLikeId().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleLikeId=").append(articleLikeId);
        sb.append(", articleId=").append(articleId);
        sb.append(", status=").append(status);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}