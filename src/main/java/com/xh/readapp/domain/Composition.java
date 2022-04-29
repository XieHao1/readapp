package com.xh.readapp.domain;

import java.io.Serializable;

/**
 * 
 * @TableName composition
 */
public class Composition implements Serializable {
    /**
     * 作文id
     */
    private Integer compositionId;

    /**
     * 作文标题
     */
    private String title;

    /**
     * 作文描述
     */
    private String describe;

    /**
     * 话题id
     */
    private Integer topicId;

    private static final long serialVersionUID = 1L;

    /**
     * 作文id
     */
    public Integer getCompositionId() {
        return compositionId;
    }

    /**
     * 作文id
     */
    public void setCompositionId(Integer compositionId) {
        this.compositionId = compositionId;
    }

    /**
     * 作文标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 作文标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 作文描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 作文描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 话题id
     */
    public Integer getTopicId() {
        return topicId;
    }

    /**
     * 话题id
     */
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
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
        Composition other = (Composition) that;
        return (this.getCompositionId() == null ? other.getCompositionId() == null : this.getCompositionId().equals(other.getCompositionId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getDescribe() == null ? other.getDescribe() == null : this.getDescribe().equals(other.getDescribe()))
            && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCompositionId() == null) ? 0 : getCompositionId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getDescribe() == null) ? 0 : getDescribe().hashCode());
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", compositionId=").append(compositionId);
        sb.append(", title=").append(title);
        sb.append(", describe=").append(describe);
        sb.append(", topicId=").append(topicId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}