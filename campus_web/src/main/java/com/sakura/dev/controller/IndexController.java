package com.sakura.dev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rc452 on 2017/5/21.
 */
@RestController
@RequestMapping
public class IndexController {
    @GetMapping
    public String index(){
        return "Hello world";
    }
}
