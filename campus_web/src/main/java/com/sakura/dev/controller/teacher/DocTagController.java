package com.sakura.dev.controller.teacher;

import com.sakura.dev.controller.dto.Result;
import com.sakura.dev.domain.CpDocTag;
import com.sakura.dev.domain.CpPoorBuild;
import com.sakura.dev.domain.CpTeacher;
import com.sakura.dev.service.CpDocTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by rc452 on 2017/6/18.
 */
@RestController
@RequestMapping("/doctag")
public class DocTagController {

    @Autowired
    CpDocTagService cpDocTagService;

    @Autowired
    HttpSession session;

    @GetMapping
    public Result list(Pageable pageable){
        return Result.OK(cpDocTagService.getdocTags(pageable));
    }
    @DeleteMapping
    public Result del(@RequestBody Long[] ids){
        if (ids.length>0){
            cpDocTagService.del(ids);
            return Result.OK("删除成功", null);
        }else{
            return Result.FAILD("未选择任何选项");
        }
    }

    @GetMapping("/doc/list")
    public Result listDoc(Pageable pageable,@RequestParam Long cpDocTagId){
        CpTeacher cpTeacher = (CpTeacher) session.getAttribute("teacher");
        CpDocTag cpDocTag = cpDocTagService.getDocTagById(cpDocTagId);
        Page<CpPoorBuild> poorBuildList = cpDocTagService.getList(pageable,cpDocTag,cpTeacher);
        return Result.OK(poorBuildList);
    }


}
