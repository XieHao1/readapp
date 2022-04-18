package com.xh.readapp.vo.WeiXin;

import lombok.Data;

@Data
public class LoginVo {
    private String code;
    private String rawData;
    private String signature;
}
