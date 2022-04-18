package com.xh.readapp.domain;

import java.io.Serializable;

/**
 * 
 * @TableName tag
 */
public class Tag implements Serializable {
    /**
     * 标签主键
     */
    private String tagId;

    /**
     * 标签图标地址
     */
    private String avatar;

    /**
     * 标签的名字
     */
    private String tagName;

    private String categoryId;

    private static final long serialVersionUID = 1L;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 标签主键
     */
    public String getTagId() {
        return tagId;
    }

    /**
     * 标签主键
     */
    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    /**
     * 标签图标地址
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 标签图标地址
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 标签的名字
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 标签的名字
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
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
        Tag other = (Tag) that;
        return (this.getTagId() == null ? other.getTagId() == null : this.getTagId().equals(other.getTagId()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getTagName() == null ? other.getTagName() == null : this.getTagName().equals(other.getTagName()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTagId() == null) ? 0 : getTagId().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getTagName() == null) ? 0 : getTagName().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tagId=").append(tagId);
        sb.append(", avatar=").append(avatar);
        sb.append(", tagName=").append(tagName);
        sb.append(", categoryId=").append(tagName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}