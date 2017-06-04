package com.sakura.dev.controller;

import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.service.CpStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rc452 on 2017/5/30.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    CpStudentService cpStudentService;

    @GetMapping
    public Object info(){
        Object detail = SecurityContextHolder.getContext().getAuthentication().getDetails();
        return detail;
    }

    @PostMapping("/rest_password")
    public Object modify(CpStudent arg){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return cpStudentService.restPassowrd(user.getUsername());
    }
}
