package com.xh.readapp.vo.draft;

import lombok.Data;

import java.io.Serializable;

@Data
public class DraftParams implements Serializable {
    private String title;
    private String userId;
    private String content;
    private String contentHtml;
}
