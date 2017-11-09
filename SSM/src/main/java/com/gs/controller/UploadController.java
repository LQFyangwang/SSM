package com.gs.controller;

import com.gs.common.PathUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class UploadController {

    /**
     * MultipartFile接口能够接收由post请求上传的文件内容
     * @return
     */
    @PostMapping("upload")
    public String upload(MultipartFile file) {
        System.out.println(file.getContentType());
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        try {
            file.transferTo(new File(PathUtils.mkUploads(), file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "\"msg\":\"上传成功\"";
    }

}
