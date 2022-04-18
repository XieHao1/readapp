package com.xh.readapp.domain;

import java.io.Serializable;

/**
 * 
 * @TableName focus
 */
public class Focus implements Serializable {
    /**
     * 关注id
     */
    private String focusId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 被关注的用户id
     */
    private String toUserId;

    /**
     * 关注的时间
     */
    private String focusTime;

    private static final long serialVersionUID = 1L;

    /**
     * 关注id
     */
    public String getFocusId() {
        return focusId;
    }

    /**
     * 关注id
     */
    public void setFocusId(String focusId) {
        this.focusId = focusId;
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
     * 被关注的用户id
     */
    public String getToUserId() {
        return toUserId;
    }

    /**
     * 被关注的用户id
     */
    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * 关注的时间
     */
    public String getFocusTime() {
        return focusTime;
    }

    /**
     * 关注的时间
     */
    public void setFocusTime(String focusTime) {
        this.focusTime = focusTime;
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
        Focus other = (Focus) that;
        return (this.getFocusId() == null ? other.getFocusId() == null : this.getFocusId().equals(other.getFocusId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getToUserId() == null ? other.getToUserId() == null : this.getToUserId().equals(other.getToUserId()))
            && (this.getFocusTime() == null ? other.getFocusTime() == null : this.getFocusTime().equals(other.getFocusTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFocusId() == null) ? 0 : getFocusId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getToUserId() == null) ? 0 : getToUserId().hashCode());
        result = prime * result + ((getFocusTime() == null) ? 0 : getFocusTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", focusId=").append(focusId);
        sb.append(", userId=").append(userId);
        sb.append(", toUserId=").append(toUserId);
        sb.append(", focusTime=").append(focusTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}