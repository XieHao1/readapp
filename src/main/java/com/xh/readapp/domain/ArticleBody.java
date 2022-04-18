package com.xh.readapp.domain;

import java.io.Serializable;

/**
 * 
 * @TableName article_body
 */
public class ArticleBody implements Serializable {
    /**
     * 使用32位UUID作为主键
     */
    private String bodyId;

    /**
     * 内容
     */
    private String content;

    /**
     * 内容的HTML格式
     */
    private String contentHtml;

    /**
     * 文章id
     */
    private String articleId;

    private static final long serialVersionUID = 1L;

    /**
     * 使用32位UUID作为主键
     */
    public String getBodyId() {
        return bodyId;
    }

    /**
     * 使用32位UUID作为主键
     */
    public void setBodyId(String bodyId) {
        this.bodyId = bodyId;
    }

    /**
     * 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 内容的HTML格式
     */
    public String getContentHtml() {
        return contentHtml;
    }

    /**
     * 内容的HTML格式
     */
    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
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
        ArticleBody other = (ArticleBody) that;
        return (this.getBodyId() == null ? other.getBodyId() == null : this.getBodyId().equals(other.getBodyId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getContentHtml() == null ? other.getContentHtml() == null : this.getContentHtml().equals(other.getContentHtml()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBodyId() == null) ? 0 : getBodyId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getContentHtml() == null) ? 0 : getContentHtml().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bodyId=").append(bodyId);
        sb.append(", content=").append(content);
        sb.append(", contentHtml=").append(contentHtml);
        sb.append(", articleId=").append(articleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}