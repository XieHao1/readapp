package com.xh.readapp.domain;

import java.io.Serializable;

/**
 * 
 * @TableName article
 */
public class Article implements Serializable {
    /**
     * 使用32位UUID作为主键
     */
    private String articleId;

    /**
     * 评论数量
     */
    private Integer commentCounts;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 标题
     */
    private String title;

    /**
     * 浏览数量
     */
    private Integer viewCounts;

    /**
     * 作者id
     */
    private String userId;

    /**
     * 内容id
     */
    private String bodyId;

    /**
     * 类别id
     */
    private String categoryId;

    /**
     * 点赞数
     */
    private Integer likeCounts;

    private String audioUrl;

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    /**
     * 使用32位UUID作为主键
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * 使用32位UUID作为主键
     */
    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    /**
     * 评论数量
     */
    public Integer getCommentCounts() {
        return commentCounts;
    }

    /**
     * 评论数量
     */
    public void setCommentCounts(Integer commentCounts) {
        this.commentCounts = commentCounts;
    }

    /**
     * 创建时间
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 浏览数量
     */
    public Integer getViewCounts() {
        return viewCounts;
    }

    /**
     * 浏览数量
     */
    public void setViewCounts(Integer viewCounts) {
        this.viewCounts = viewCounts;
    }

    /**
     * 作者id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 作者id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 内容id
     */
    public String getBodyId() {
        return bodyId;
    }

    /**
     * 内容id
     */
    public void setBodyId(String bodyId) {
        this.bodyId = bodyId;
    }

    /**
     * 类别id
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 类别id
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 点赞数
     */
    public Integer getLikeCounts() {
        return likeCounts;
    }

    /**
     * 点赞数
     */
    public void setLikeCounts(Integer likeCounts) {
        this.likeCounts = likeCounts;
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
        Article other = (Article) that;
        return (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getCommentCounts() == null ? other.getCommentCounts() == null : this.getCommentCounts().equals(other.getCommentCounts()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getViewCounts() == null ? other.getViewCounts() == null : this.getViewCounts().equals(other.getViewCounts()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getBodyId() == null ? other.getBodyId() == null : this.getBodyId().equals(other.getBodyId()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getLikeCounts() == null ? other.getLikeCounts() == null : this.getLikeCounts().equals(other.getLikeCounts()))
            && (this.getAudioUrl() == null ? other.getAudioUrl() == null : this.getAudioUrl().equals(other.getAudioUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getCommentCounts() == null) ? 0 : getCommentCounts().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getViewCounts() == null) ? 0 : getViewCounts().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getBodyId() == null) ? 0 : getBodyId().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getLikeCounts() == null) ? 0 : getLikeCounts().hashCode());
        result = prime * result + ((getAudioUrl() == null) ? 0 : getAudioUrl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", commentCounts=").append(commentCounts);
        sb.append(", createDate=").append(createDate);
        sb.append(", title=").append(title);
        sb.append(", viewCounts=").append(viewCounts);
        sb.append(", userId=").append(userId);
        sb.append(", bodyId=").append(bodyId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", likeCounts=").append(likeCounts);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", audioUrl=").append(audioUrl);
        sb.append("]");
        return sb.toString();
    }
}