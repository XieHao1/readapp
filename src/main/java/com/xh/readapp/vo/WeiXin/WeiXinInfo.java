package com.xh.readapp.vo.WeiXin;

import lombok.Data;

import java.io.Serializable;

@Data
public class WeiXinInfo implements Serializable {
    private String session_key;
    private String openid;
}
