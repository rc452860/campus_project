package com.sakura.dev.controller;

import com.sakura.dev.domain.CpStudent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rc452 on 2017/5/30.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public Object info(){
        Object detail = SecurityContextHolder.getContext().getAuthentication().getDetails();
        return detail;
    }
}
