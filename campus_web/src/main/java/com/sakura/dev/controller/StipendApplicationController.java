package com.sakura.dev.controller;

import com.sakura.dev.domain.CpStipendApplication;
import com.sakura.dev.service.CpStipendApplicationService;
import com.sakura.dev.utils.Result;
import com.sakura.dev.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 助学金申请Controller
 * Created by yth on 2017/6/5.
 */
@RestController
@RequestMapping("/stipend")
public class StipendApplicationController {
    @Autowired
    CpStipendApplicationService cpStipendApplicationService;
    @PostMapping("/application")
    public Result stipendApplication(@RequestBody CpStipendApplication cpStipendApplication){
        if (cpStipendApplicationService.StipendApplication(cpStipendApplication)){
            return ResultUtils.success();
        }
        return ResultUtils.error(1,"申请失败");
    }
}
