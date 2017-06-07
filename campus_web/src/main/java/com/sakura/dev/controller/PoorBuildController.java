package com.sakura.dev.controller;

import com.sakura.dev.controller.dto.AuditDto;
import com.sakura.dev.domain.CpPoorBuild;
import com.sakura.dev.service.CpPoorBuildService;
import com.sakura.dev.utils.Result;
import com.sakura.dev.utils.ResultUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 贫困建档Controller
 * Created by yth on 2017/6/5.
 */
@RestController
@RequestMapping("/poorBuild")
public class PoorBuildController {
    @Autowired
    CpPoorBuildService cpPoorBuildService;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @PostMapping("/application")
    public Result stipendApplication(@RequestBody CpPoorBuild cpPoorBuild){
        if (cpPoorBuildService.insertStudent(cpPoorBuild)){
            return ResultUtils.success();
        }
        return ResultUtils.error(1,"申请失败");
    }

    /**
     * 教师审核
     * @param auditDto
     * @return
     */
    @PostMapping("/audit")
    public Result Audit(@RequestBody AuditDto auditDto){
        CpPoorBuild cpPoorBuild = cpPoorBuildService.getStudent(auditDto.getCpCard());
        if (auditDto.getIdentity().equals("counselor")){
            if (cpPoorBuild!=null){
                cpPoorBuild.setCpCounselorResult(1);
                if (cpPoorBuildService.insertStudent(cpPoorBuild)){
                    return ResultUtils.success(0,"审核成功！");
                }
            }
        }
        return ResultUtils.error(1,"审核失败！");
    }

}
