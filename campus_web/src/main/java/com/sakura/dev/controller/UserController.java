package com.sakura.dev.controller;

import com.sakura.dev.controller.dto.BaseResponse;
import com.sakura.dev.controller.dto.LoginRequest;
import com.sakura.dev.controller.dto.UserAccount;
import com.sakura.dev.domain.CpPoorBuild;
import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.service.CpStudentService;
import com.sakura.dev.utils.Result;
import com.sakura.dev.utils.ResultUtils;
import com.sun.prism.impl.BaseResourceFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by rc452 on 2017/5/30.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    CpStudentService cpStudentService;
    @Autowired
    HttpSession session;

    private Logger logger = Logger.getLogger(this.getClass().getName());
    @PostMapping("/login")
    public BaseResponse login(@RequestBody  LoginRequest loginRequest){
        BaseResponse baseResponse = new BaseResponse();
        CpStudent cpStudent = cpStudentService.login(loginRequest);
        if (cpStudent!=null){
            baseResponse.setStatus(BaseResponse.SUCCESS);
            baseResponse.setData(cpStudent);
            session.setAttribute("user",cpStudent);
        }
        return baseResponse;
    }
    /**
     * 重置密码
     * @param arg
     * @return
     */
    @PostMapping("/rest_password")
    public BaseResponse modify(CpStudent arg){
        logger.info("重置密码");
        CpStudent cpStudent = (CpStudent) session.getAttribute("user");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(BaseResponse.SUCCESS);
        baseResponse.setData("重置密码成功");
        return baseResponse;
    }

    /**
     * 修改密码
     * @param account
     * @return
     */
    @PostMapping("/modify/password")
    public Result modifyPassword(@RequestBody UserAccount account){
        logger.info("修改密码");
        CpStudent student = (CpStudent) session.getAttribute("user");
        if (cpStudentService.updatePassword(student,account)){
            return ResultUtils.success();
        }else {
            return ResultUtils.error(1,"修改失败！");
        }
    }

    /**
     * 获取学生基本信息
     * @return
     */
    @GetMapping("/getinfo")
    public Result<CpStudent>  getStudengDetail(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CpStudent cpStudent = cpStudentService.get(user.getUsername());
        if (cpStudent!=null){
            return ResultUtils.success(cpStudent);
        }
        return ResultUtils.error(1,"用不存在");
    }
}
