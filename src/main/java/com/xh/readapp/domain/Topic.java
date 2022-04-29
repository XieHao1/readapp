package com.xh.readapp.domain;


import java.io.Serializable;

/**
 * 
 * @TableName topic
 */
public class Topic implements Serializable {
    /**
     * 真题类别id
     */
    private Integer topicId;

    /**
     * 真题类别名字
     */
    private String topicName;

    private static final long serialVersionUID = 1L;

    /**
     * 真题类别id
     */
    public Integer getTopicId() {
        return topicId;
    }

    /**
     * 真题类别id
     */
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    /**
     * 真题类别名字
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * 真题类别名字
     */
    public void setTopicName(String topicName) {
        this.topicName = topicName;
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
        Topic other = (Topic) that;
        return (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
            && (this.getTopicName() == null ? other.getTopicName() == null : this.getTopicName().equals(other.getTopicName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getTopicName() == null) ? 0 : getTopicName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", topicId=").append(topicId);
        sb.append(", topicName=").append(topicName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}