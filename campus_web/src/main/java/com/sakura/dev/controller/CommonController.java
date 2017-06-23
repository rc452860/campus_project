package com.sakura.dev.controller;

import com.sakura.dev.controller.dto.Result;
import com.sakura.dev.domain.CpFile;
import com.sakura.dev.service.CpFileService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by rc452 on 2017/6/19.
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    CpFileService cpFileService;

    @PostMapping
    public Result fileUpload(MultipartFile file){
        CpFile cpFile = cpFileService.store(file);
        if(cpFile!=null){
            return Result.OK(cpFile);
        }else{
            return Result.ERR("上传出错");
        }
    }

    @PostMapping("/v2")
    public Result fileUpload2(@RequestParam("file") MultipartFile file, @RequestParam("name") String name){
        CpFile cpFile = cpFileService.store(file);
        if(cpFile!=null){
            return Result.OK(cpFile);
        }else{
            return Result.ERR("上传出错");
        }
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
