package com.xh.readapp.domain;

import java.io.Serializable;

/**
 * 
 * @TableName shop
 */
public class Shop implements Serializable {
    /**
     * 用户为商家的id
     */
    private String userShopId;

    /**
     * 商家的标签
     */
    private String label;

    private static final long serialVersionUID = 1L;

    /**
     * 用户为商家的id
     */
    public String getUserShopId() {
        return userShopId;
    }

    /**
     * 用户为商家的id
     */
    public void setUserShopId(String userShopId) {
        this.userShopId = userShopId;
    }

    /**
     * 商家的标签
     */
    public String getLabel() {
        return label;
    }

    /**
     * 商家的标签
     */
    public void setLabel(String label) {
        this.label = label;
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
        Shop other = (Shop) that;
        return (this.getUserShopId() == null ? other.getUserShopId() == null : this.getUserShopId().equals(other.getUserShopId()))
            && (this.getLabel() == null ? other.getLabel() == null : this.getLabel().equals(other.getLabel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserShopId() == null) ? 0 : getUserShopId().hashCode());
        result = prime * result + ((getLabel() == null) ? 0 : getLabel().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userShopId=").append(userShopId);
        sb.append(", label=").append(label);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}