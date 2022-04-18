package com.xh.readapp.Controller;

import com.xh.readapp.dictionary.ErrorEnum;
import com.xh.readapp.util.QINiuYunUtils;
import com.xh.readapp.util.ResultJson;
import com.xh.readapp.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private QINiuYunUtils qiNiuYunUtils;

    @PostMapping()
    public ResultJson upload(@RequestParam(value = "image",required = false) MultipartFile file){
        return getResultJson(file);
    }

    @PostMapping("/mp3")
    public ResultJson uploadMp3(@RequestParam(value = "mp3",required = false) MultipartFile file){
        return getResultJson(file);
    }

    private ResultJson getResultJson(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileName = UUIDUtil.getUUID() + "."
                + StringUtils.substringAfterLast(originalFilename,".");
        boolean upload = qiNiuYunUtils.upload(file, fileName);
        if(upload){
            return ResultJson.success(QINiuYunUtils.URL+fileName);
        }
        return ResultJson.fail(ErrorEnum.UPLOAD_IMAGE_ERROR.getCode(), ErrorEnum.UPDATE_ARTICLE_ERROR.getMsg());
    }
}
