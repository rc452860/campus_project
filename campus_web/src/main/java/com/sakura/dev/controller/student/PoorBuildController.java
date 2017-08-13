package com.sakura.dev.controller.student;

import com.sakura.dev.controller.dto.Result;
import com.sakura.dev.domain.CpPoorBuild;
import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.service.CpDocTagService;
import com.sakura.dev.service.CpPoorBuildService;
import com.sakura.dev.service.CpStudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 贫困建档Controller
 * Created by yth on 2017/6/5.
 */
@RestController
@RequestMapping("/poorBuild")
public class PoorBuildController {
    @Autowired
    CpPoorBuildService cpPoorBuildService;
    @Autowired
    CpStudentService cpStudentService;
    @Autowired
    CpDocTagService cpDocTagService;


    @Autowired
    HttpSession session;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @GetMapping
    public Result apply(){
        if(cpDocTagService.existOpen()){
            CpStudent cpStudent = (CpStudent) session.getAttribute("student");
            CpPoorBuild cpPoorBuild =  cpPoorBuildService.getBaseInfo(cpStudent);
            if(cpPoorBuild!=null){
                return Result.OK(cpPoorBuild);
            }else {
                return Result.ERR("找不到学生基本信息");
            }
        }else{
            return Result.ERR("暂未开放");
        }
    }

    @PostMapping("/application")
    public Result stipendApplication(@RequestBody CpPoorBuild cpPoorBuild){
        if(cpPoorBuild.getCpId()==null&&cpPoorBuildService.hasCurrentApply(cpPoorBuild.getCpIdCardNo())){
            return Result.ERR("档案已存在");
        }
        if (cpPoorBuildService.save(cpPoorBuild)){
            return Result.OK("申请成功");
        }
        return Result.ERR("申请失败");
    }

    @PostMapping("/hasApply")
    public Result hasApply(){
        CpStudent cpStudent = (CpStudent) session.getAttribute("student");
        if(cpPoorBuildService.hasCurrentApply(cpStudent.getCpIdCardNo())){
            return Result.OK(true);
        }else{
            return Result.OK(false);
        }
    }
    @GetMapping("/status")
    public Result getState(){
        CpStudent cpStudent = (CpStudent) session.getAttribute("student");
        return Result.OK(cpPoorBuildService.getStatus(cpStudent));
    }
    @GetMapping("/open")
    public Result checkOpen(){
        if(cpDocTagService.existOpen()){
            return Result.OK("");
        }else{
            return Result.ERR("暂未开放");
        }
    }


    @GetMapping("/review")
    public Result review(){
        CpStudent cpStudent = (CpStudent) session.getAttribute("student");
        CpPoorBuild cpPoorBuild = cpPoorBuildService.getCurrentApply(cpStudent.getCpIdCardNo());
        if(cpPoorBuild!=null){
            return Result.OK(cpPoorBuild);
        }else{
            return Result.ERR("没有档案提交");
        }
    }

}
