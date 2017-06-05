package com.sakura.dev.controller;

import com.sakura.dev.domain.CpPoorBuild;
import com.sakura.dev.domain.CpStipendApplication;
import com.sakura.dev.service.CpPoorBuildService;
import com.sakura.dev.service.CpStipendApplicationService;
import com.sakura.dev.utils.Result;
import com.sakura.dev.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 贫困建档Controller
 * Created by yth on 2017/6/5.
 */
@RestController
@RequestMapping("/stipend")
public class PoorBuildController {
    @Autowired
    CpPoorBuildService cpPoorBuildService;
    @PostMapping("/application")
    public Result stipendApplication(@RequestBody CpPoorBuild cpPoorBuild){
        if (cpPoorBuildService.StipendApplication(cpPoorBuild)){
            return ResultUtils.success();
        }
        return ResultUtils.error(1,"申请失败");
    }
}
