package com.sakura.dev.controller.student;

import com.sakura.dev.controller.dto.PoorBuildRequest;
import com.sakura.dev.controller.dto.Result;
import com.sakura.dev.service.CpStipendApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Result poorBuildApplication(@RequestBody PoorBuildRequest poorBuildRequest){

        return Result.ERR("申请失败");
    }

}
