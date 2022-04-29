package com.xh.readapp.domain;


import java.io.Serializable;

/**
 * 
 * @TableName composition_body
 */
public class CompositionBody implements Serializable {
    /**
     * 作文内容id
     */
    private Integer compositionBodyId;

    /**
     * 作文id
     */
    private Integer compositionId;

    /**
     * 作文内容
     */
    private String compositionBody;

    private static final long serialVersionUID = 1L;

    /**
     * 作文内容id
     */
    public Integer getCompositionBodyId() {
        return compositionBodyId;
    }

    /**
     * 作文内容id
     */
    public void setCompositionBodyId(Integer compositionBodyId) {
        this.compositionBodyId = compositionBodyId;
    }

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
     * 作文内容
     */
    public String getCompositionBody() {
        return compositionBody;
    }

    /**
     * 作文内容
     */
    public void setCompositionBody(String compositionBody) {
        this.compositionBody = compositionBody;
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
        CompositionBody other = (CompositionBody) that;
        return (this.getCompositionBodyId() == null ? other.getCompositionBodyId() == null : this.getCompositionBodyId().equals(other.getCompositionBodyId()))
            && (this.getCompositionId() == null ? other.getCompositionId() == null : this.getCompositionId().equals(other.getCompositionId()))
            && (this.getCompositionBody() == null ? other.getCompositionBody() == null : this.getCompositionBody().equals(other.getCompositionBody()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCompositionBodyId() == null) ? 0 : getCompositionBodyId().hashCode());
        result = prime * result + ((getCompositionId() == null) ? 0 : getCompositionId().hashCode());
        result = prime * result + ((getCompositionBody() == null) ? 0 : getCompositionBody().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", compositionBodyId=").append(compositionBodyId);
        sb.append(", compositionId=").append(compositionId);
        sb.append(", compositionBody=").append(compositionBody);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}