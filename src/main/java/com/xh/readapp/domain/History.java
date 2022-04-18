package com.xh.readapp.domain;

import java.io.Serializable;

/**
 * 
 * @TableName history
 */
public class History implements Serializable {
    /**
     * 32位UUID
     */
    private String timeId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 时间
     */
    private String time;

    /**
     * 个人感受
     */
    private String feel;

    /**
     * 语句摘录
     */
    private String excerpt;

    private String readingTime;

    private String bookmark;

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    public String getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    /**
     * 32位UUID
     */
    public String getTimeId() {
        return timeId;
    }

    /**
     * 32位UUID
     */
    public void setTimeId(String timeId) {
        this.timeId = timeId;
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
     * 时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 时间
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 个人感受
     */
    public String getFeel() {
        return feel;
    }

    /**
     * 个人感受
     */
    public void setFeel(String feel) {
        this.feel = feel;
    }

    /**
     * 语句摘录
     */
    public String getExcerpt() {
        return excerpt;
    }

    /**
     * 语句摘录
     */
    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
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
        History other = (History) that;
        return (this.getTimeId() == null ? other.getTimeId() == null : this.getTimeId().equals(other.getTimeId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getFeel() == null ? other.getFeel() == null : this.getFeel().equals(other.getFeel()))
            && (this.getExcerpt() == null ? other.getExcerpt() == null : this.getExcerpt().equals(other.getExcerpt()))
            && (this.getReadingTime() == null ? other.getReadingTime() == null : this.getReadingTime().equals(other.getReadingTime()))
            && (this.getBookmark() == null ? other.getBookmark() == null : this.getBookmark().equals(other.getBookmark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTimeId() == null) ? 0 : getTimeId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getFeel() == null) ? 0 : getFeel().hashCode());
        result = prime * result + ((getExcerpt() == null) ? 0 : getExcerpt().hashCode());
        result = prime * result + ((getReadingTime() == null) ? 0 : getReadingTime().hashCode());
        result = prime * result + ((getBookmark() == null) ? 0 : getBookmark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", timeId=").append(timeId);
        sb.append(", userId=").append(userId);
        sb.append(", time=").append(time);
        sb.append(", feel=").append(feel);
        sb.append(", excerpt=").append(excerpt);
        sb.append(", readingTime=").append(readingTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", bookmark=").append(bookmark);
        sb.append("]");
        return sb.toString();
    }
}