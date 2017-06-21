package com.sakura.dev.controller;

import com.sakura.dev.controller.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by rc452 on 2017/6/19.
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @PostMapping
    public Result fileUpload(MultipartFile file){
        return Result.ERR("暂未开放");
    }
    @GetMapping
    public Result test(MultipartFile file){
        return Result.ERR("暂未开放C");
    }
    @GetMapping("/b")
    public Result test2(MultipartFile file){
        return Result.ERR("暂未开放B");
    }
}
