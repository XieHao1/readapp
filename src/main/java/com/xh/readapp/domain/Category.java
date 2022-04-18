package com.xh.readapp.domain;

import java.io.Serializable;

/**
 * 
 * @TableName category
 */
public class Category implements Serializable {
    /**
     * 类别主键
     */
    private String categoryId;

    /**
     * 类别图片地址
     */
    private String avatar;

    /**
     * 类别名字
     */
    private String categoryName;

    /**
     * 类别描述
     */
    private String description;

    private static final long serialVersionUID = 1L;

    /**
     * 类别主键
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 类别主键
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 类别图片地址
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 类别图片地址
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 类别名字
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 类别名字
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 类别描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 类别描述
     */
    public void setDescription(String description) {
        this.description = description;
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
        Category other = (Category) that;
        return (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getCategoryName() == null ? other.getCategoryName() == null : this.getCategoryName().equals(other.getCategoryName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getCategoryName() == null) ? 0 : getCategoryName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", categoryId=").append(categoryId);
        sb.append(", avatar=").append(avatar);
        sb.append(", categoryName=").append(categoryName);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}