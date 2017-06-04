package com.sakura.dev.controller;

import com.sakura.dev.controller.dto.BaseResult;
import com.sakura.dev.controller.dto.IResult;
import com.sakura.dev.controller.dto.UserAccount;
import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.service.CpStudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rc452 on 2017/5/30.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    CpStudentService cpStudentService;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @GetMapping
    public Object info(){
        Object detail = SecurityContextHolder.getContext().getAuthentication().getDetails();
        return detail;
    }

    /**
     * 重置密码
     * @param arg
     * @return
     */
    @PostMapping("/rest_password")
    public Object modify(CpStudent arg){
        logger.info("重置密码");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return cpStudentService.restPassowrd(user.getUsername());
    }

    /**
     * 修改密码
     * @param account
     * @return
     */
    @PostMapping("/modify/password")
    public IResult modifyPassword(@RequestBody UserAccount account){
        logger.info("修改密码");
        BaseResult result = new BaseResult();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  //获取当前用户
        CpStudent student = cpStudentService.get(user.getUsername());
        if (cpStudentService.updatePassword(student,account)){
            result.setResult("success");
        }else {
            result.setResult("failure");
        }
        return result;
    }
}
