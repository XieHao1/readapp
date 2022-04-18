package com.xh.readapp.domain;

import java.io.Serializable;

/**
 * 
 * @TableName user
 */

public class User implements Serializable {
    /**
     * 微信传递id
     */
    private String userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别，0为男，1为女
     */
    private Integer gender;

    /**
     * 语言
     */
    private String language;

    /**
     * 居住地
     */
    private String province;

    /**
     * 头像Url
     */
    private String avatarUrl;

    /**
     * 是否为商家 0表示否，1表示是
     */
    private Integer isShop;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 是否删除，0表示删除，1表示未删除
     */
    private Integer deleted;

    /**
     * 最后登录时间
     */
    private String lastLogin;

    /**
     * 学校名称
     */
    private String school;

    /**
     * 关注数
     */
    private Integer attentionCounts;

    /**
     * 粉丝数
     */
    private Integer fansCounts;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 背景图
     */
    private String background;

    private String introduction;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    private static final long serialVersionUID = 1L;

    /**
     * 微信传递id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 微信传递id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 性别，0为男，1为女
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 性别，0为男，1为女
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 语言
     */
    public void setLanguage(String language) {
        this.language = language;
    }


    /**
     * 居住地
     */
    public String getProvince() {
        return province;
    }

    /**
     * 居住地
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 头像Url
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 头像Url
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 是否为商家 0表示否，1表示是
     */
    public Integer getIsShop() {
        return isShop;
    }

    /**
     * 是否为商家 0表示否，1表示是
     */
    public void setIsShop(Integer isShop) {
        this.isShop = isShop;
    }

    /**
     * 创建时间
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     * @param createDate
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * 是否删除，0表示删除，1表示未删除
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 是否删除，0表示删除，1表示未删除
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 最后登录时间
     */
    public String getLastLogin() {
        return lastLogin;
    }

    /**
     * 最后登录时间
     */
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * 学校名称
     */
    public String getSchool() {
        return school;
    }

    /**
     * 学校名称
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * 关注数
     */
    public Integer getAttentionCounts() {
        return attentionCounts;
    }

    /**
     * 关注数
     */
    public void setAttentionCounts(Integer attentionCounts) {
        this.attentionCounts = attentionCounts;
    }

    /**
     * 粉丝数
     */
    public Integer getFansCounts() {
        return fansCounts;
    }

    /**
     * 粉丝数
     */
    public void setFansCounts(Integer fansCounts) {
        this.fansCounts = fansCounts;
    }

    /**
     * 生日
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 生日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 背景图
     */
    public String getBackground() {
        return background;
    }

    /**
     * 背景图
     */
    public void setBackground(String background) {
        this.background = background;
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
        User other = (User) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getLanguage() == null ? other.getLanguage() == null : this.getLanguage().equals(other.getLanguage()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getAvatarUrl() == null ? other.getAvatarUrl() == null : this.getAvatarUrl().equals(other.getAvatarUrl()))
            && (this.getIsShop() == null ? other.getIsShop() == null : this.getIsShop().equals(other.getIsShop()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getLastLogin() == null ? other.getLastLogin() == null : this.getLastLogin().equals(other.getLastLogin()))
            && (this.getSchool() == null ? other.getSchool() == null : this.getSchool().equals(other.getSchool()))
            && (this.getAttentionCounts() == null ? other.getAttentionCounts() == null : this.getAttentionCounts().equals(other.getAttentionCounts()))
            && (this.getFansCounts() == null ? other.getFansCounts() == null : this.getFansCounts().equals(other.getFansCounts()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getBackground() == null ? other.getBackground() == null : this.getBackground().equals(other.getBackground()))
            && (this.getIntroduction() == null ? other.getIntroduction() == null : this.getIntroduction().equals(other.getIntroduction()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getLanguage() == null) ? 0 : getLanguage().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getAvatarUrl() == null) ? 0 : getAvatarUrl().hashCode());
        result = prime * result + ((getIsShop() == null) ? 0 : getIsShop().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getLastLogin() == null) ? 0 : getLastLogin().hashCode());
        result = prime * result + ((getSchool() == null) ? 0 : getSchool().hashCode());
        result = prime * result + ((getAttentionCounts() == null) ? 0 : getAttentionCounts().hashCode());
        result = prime * result + ((getFansCounts() == null) ? 0 : getFansCounts().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getBackground() == null) ? 0 : getBackground().hashCode());
        result = prime * result + ((getIntroduction() == null) ? 0 : getIntroduction().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", nickname=").append(nickname);
        sb.append(", gender=").append(gender);
        sb.append(", language=").append(language);
        sb.append(", province=").append(province);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", isShop=").append(isShop);
        sb.append(", createDate=").append(createDate);
        sb.append(", deleted=").append(deleted);
        sb.append(", lastLogin=").append(lastLogin);
        sb.append(", school=").append(school);
        sb.append(", attentionCounts=").append(attentionCounts);
        sb.append(", fansCounts=").append(fansCounts);
        sb.append(", birthday=").append(birthday);
        sb.append(", background=").append(background);
        sb.append(", introduction=").append(introduction);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}