package com.xh.readapp.domain;

import java.io.Serializable;

/**
 * 
 * @TableName article_tag
 */
public class ArticleTag implements Serializable {
    /**
     * 连接id
     */
    private String id;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 标签id
     */
    private String tagId;

    private static final long serialVersionUID = 1L;

    /**
     * 连接id
     */
    public String getId() {
        return id;
    }

    /**
     * 连接id
     */
    public void setId(String id) {
        this.id = id;
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
     * 标签id
     */
    public String getTagId() {
        return tagId;
    }

    /**
     * 标签id
     */
    public void setTagId(String tagId) {
        this.tagId = tagId;
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
        ArticleTag other = (ArticleTag) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getTagId() == null ? other.getTagId() == null : this.getTagId().equals(other.getTagId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getTagId() == null) ? 0 : getTagId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", articleId=").append(articleId);
        sb.append(", tagId=").append(tagId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}