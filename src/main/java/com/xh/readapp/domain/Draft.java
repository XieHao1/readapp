package com.xh.readapp.domain;

import java.io.Serializable;

/**
 * 
 * @TableName draft
 */
public class Draft implements Serializable {
    /**
     * 草稿主键
     */
    private String draftId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章内容的HTML格式 
     */
    private String contentHtml;

    private static final long serialVersionUID = 1L;

    /**
     * 草稿主键
     */
    public String getDraftId() {
        return draftId;
    }

    /**
     * 草稿主键
     */
    public void setDraftId(String draftId) {
        this.draftId = draftId;
    }

    /**
     * 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 文章标题
     */
    public void setTitle(String title) {
        this.title = title;
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
     * 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 文章内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 文章内容的HTML格式 
     */
    public String getContentHtml() {
        return contentHtml;
    }

    /**
     * 文章内容的HTML格式 
     */
    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
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
        Draft other = (Draft) that;
        return (this.getDraftId() == null ? other.getDraftId() == null : this.getDraftId().equals(other.getDraftId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getContentHtml() == null ? other.getContentHtml() == null : this.getContentHtml().equals(other.getContentHtml()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDraftId() == null) ? 0 : getDraftId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getContentHtml() == null) ? 0 : getContentHtml().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", draftId=").append(draftId);
        sb.append(", title=").append(title);
        sb.append(", userId=").append(userId);
        sb.append(", content=").append(content);
        sb.append(", contentHtml=").append(contentHtml);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}