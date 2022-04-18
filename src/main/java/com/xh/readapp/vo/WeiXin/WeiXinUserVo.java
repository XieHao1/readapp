package com.xh.readapp.vo.WeiXin;

import lombok.Data;

import java.io.Serializable;

@Data
public class WeiXinUserVo implements Serializable {
    private String openId;
    private String nickName;
    private Integer gender;
    private String province;
    private String avatarUrl;
    private String language;

}
