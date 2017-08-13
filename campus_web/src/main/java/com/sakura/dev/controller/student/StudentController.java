package com.sakura.dev.controller.student;

import com.sakura.dev.controller.dto.LoginRequest;
import com.sakura.dev.controller.dto.Result;
import com.sakura.dev.controller.dto.UserAccount;
import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.service.CpStudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by rc452 on 2017/5/30.
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    CpStudentService cpStudentService;
    @Autowired
    HttpSession session;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * 学生登录
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody  LoginRequest loginRequest){
        CpStudent cpStudent = cpStudentService.login(loginRequest);
        if (cpStudent!=null){
            session.setAttribute("student",cpStudent);
            return Result.OK(session.getId());
        }
        return Result.FAILD("登陆失败");
    }
    /**
     * 重置密码
     * @param arg
     * @return
     */
    @PostMapping("/rest_password")
    public Result modify(CpStudent arg){
        logger.info("重置密码");
        CpStudent cpStudent = (CpStudent) session.getAttribute("student");
        Result result = new Result();
        result.setStatus(Result.SUCCESS);
        result.setData("重置密码成功");
        return result;
    }

    /**
     * 修改密码
     * @param account
     * @return
     */
    @PostMapping("/modify/password")
    public Result modifyPassword(@RequestBody UserAccount account){
        logger.info("修改密码");
        CpStudent student = (CpStudent) session.getAttribute("student");
        if (cpStudentService.updatePassword(student,account)){
            return Result.OK("修改成功");
        }else {
            return Result.ERR("修改失败！");
        }
    }

    /**
     * 获取学生基本信息
     * @return
     */
    @GetMapping("/getinfo")
    public Result getStudengDetail(){
        CpStudent cpStudent = (CpStudent) session.getAttribute("student");
        if (cpStudent!=null){
            return Result.OK(cpStudent);
        }
        return Result.ERR("用不存在");
    }

    /**
     * 获取学生基本信息
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public Page<CpStudent> CpStudentList(Pageable pageable) {
        return cpStudentService.findAll(pageable);
    }

}
