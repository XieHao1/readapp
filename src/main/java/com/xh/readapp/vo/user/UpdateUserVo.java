package com.xh.readapp.vo.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateUserVo implements Serializable {
    private String userId;
    private Integer gender;
    private String province;
    private String birthday;
    private String school;
    private String avatarUrl;
    private String background;
    private String introduction;
    private String nickname;
}
