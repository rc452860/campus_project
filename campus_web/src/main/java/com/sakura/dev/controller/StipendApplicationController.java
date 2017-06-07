package com.sakura.dev.controller;

import com.sakura.dev.controller.dto.PoorBuildDto;
import com.sakura.dev.domain.CpStipendApplication;
import com.sakura.dev.service.CpStipendApplicationService;
import com.sakura.dev.utils.Result;
import com.sakura.dev.utils.ResultUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 助学金申请Controller
 * Created by yth on 2017/6/5.
 */
@RestController
@RequestMapping("/stipen")
public class StipendApplicationController {
    @Autowired
    CpStipendApplicationService cpStipendApplicationService;
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass().getName());

    @PostMapping("/application")
    public Result poorBuildApplication(@RequestBody PoorBuildDto poorBuildDto){

        return ResultUtils.error(1,"申请失败");
    }

}
