package com.xh.readapp.Controller;

import com.xh.readapp.util.ResultJson;
import com.xh.readapp.vo.image.ImageVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    @GetMapping("/banner")
    public ResultJson banner(){
        List<ImageVo> list = new ArrayList<>();
        list.add(new ImageVo(1,"http://codexh.xyz/1/banner-01.jpg"));
        list.add(new ImageVo(2,"http://codexh.xyz/1/banner-02.jpg"));
        list.add(new ImageVo(3,"http://codexh.xyz/1/banner-03.jpg"));
        return ResultJson.success(list);
    }

    @GetMapping("/picture")
    public ResultJson picture(){
        List<ImageVo> list = new ArrayList<>();
        list.add(new ImageVo(1,"http://codexh.xyz/1/20220426111358.png"));
        list.add(new ImageVo(2,"http://codexh.xyz/1/20220426111414.png"));
        list.add(new ImageVo(3,"http://codexh.xyz/1/20220426111422.png"));
        return ResultJson.success(list);
    }
}
