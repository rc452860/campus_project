package com.sakura.dev.controller.teacher;

import com.sakura.dev.controller.dto.AuditReuqest;
import com.sakura.dev.controller.dto.Result;
import com.sakura.dev.service.CpDocTagService;
import com.sakura.dev.service.CpPoorBuildService;
import com.sakura.dev.service.CpTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rc452 on 2017/7/23.
 */
@RestController
@RequestMapping("/doclist")
public class DocListController {
    @Autowired
    CpPoorBuildService cpPoorBuildService;
    @Autowired
    CpDocTagService cpDocTagService;
    @Autowired
    CpTeacherService cpTeacherService;

    @GetMapping
    public Result list(Pageable pageable) {
        return Result.OK(cpPoorBuildService.getFilesOfTeacher(pageable));
    }

    @PostMapping("/audit")
    public Result audit(@RequestBody AuditReuqest auditReuqest) {
        int result = cpPoorBuildService.audit(auditReuqest);
        if (result == 1) {
            return Result.INFO("审核通过");
        } else {
            return Result.INFO("审核拒绝");
        }
    }
}

