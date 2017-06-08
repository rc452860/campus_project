package com.sakura.dev.controller.student;

import com.sakura.dev.controller.dto.AuditReuqest;
import com.sakura.dev.controller.dto.Result;
import com.sakura.dev.domain.CpPoorBuild;
import com.sakura.dev.service.CpPoorBuildService;
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
            return Result.OK("申请成功");
        }
        return Result.ERR("申请失败");
    }

    /**
     * 教师审核
     * @param auditReuqest
     * @return
     */
    @PostMapping("/audit")
    public Result Audit(@RequestBody AuditReuqest auditReuqest){
        CpPoorBuild cpPoorBuild = cpPoorBuildService.getStudent(auditReuqest.getCpCard());
        if (auditReuqest.getIdentity().equals("counselor")){
            if (cpPoorBuild!=null){
                cpPoorBuild.setCpCounselorResult(1);
                if (cpPoorBuildService.insertStudent(cpPoorBuild)){
                    return Result.OK("审核成功！");
                }
            }
        }
        return Result.ERR("审核失败！");
    }

}