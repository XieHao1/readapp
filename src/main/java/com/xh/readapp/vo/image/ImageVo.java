package com.xh.readapp.vo.image;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ImageVo implements Serializable {
    private Integer id;
    private String url;
}
