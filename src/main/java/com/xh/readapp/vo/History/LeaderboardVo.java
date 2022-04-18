package com.xh.readapp.vo.History;

import lombok.Data;

import java.io.Serializable;

@Data
public class LeaderboardVo implements Serializable {
    private String userId;
    private String nickname;
    private String avatarUrl;
    private String school;
    private String totalReadingTime;
    private String province;
}
