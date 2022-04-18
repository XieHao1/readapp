package com.xh.readapp.vo.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDataVo implements Serializable {
    private String userId;
    private String avatarUrl;
    private String nickname;
    private String province;
    private Integer attentionCounts;
    private Integer fansCounts;
    private Integer likeCounts;
    private String background;
    private String label;
    private String introduction;
    private String school;
    private String birthday;
    private Integer gender;
}
