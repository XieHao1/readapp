package com.xh.readapp.vo.WeiXin;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    private String userId;
    private String nickname;
    private Integer isShop;
    private Integer gender;
    private String avatarUrl;
}
