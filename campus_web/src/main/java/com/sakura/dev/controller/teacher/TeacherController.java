package com.sakura.dev.controller.teacher;

import com.sakura.dev.controller.dto.LoginRequest;
import com.sakura.dev.controller.dto.Result;
import com.sakura.dev.domain.CpDocTag;
import com.sakura.dev.domain.CpTeacher;
import com.sakura.dev.service.CpDocTagService;
import com.sakura.dev.service.CpTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by rc452 on 2017/6/8.
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	CpTeacherService cpTeacherService;

	@Autowired
	CpDocTagService cpDocTagService;

	@Autowired
	HttpSession session;

	@PostMapping("/login")
	public Result login(@RequestBody LoginRequest loginRequest){
		CpTeacher cpTeacher = cpTeacherService.login(loginRequest);
		if (cpTeacher!=null){
			session.setAttribute("teacher",cpTeacher);
			return Result.OK(session.getId());
		}
		return Result.FAILD("登陆失败,请检查账号或密码是否输入正确");
	}

	@PostMapping("/open")
	public Result open(@RequestBody CpDocTag cpDocTag){
		if (cpDocTag.getCpId() == null && !cpDocTagService.existOpen()) {
			cpDocTagService.open(cpDocTag);
			return Result.OK("开放成功");
		} else if (cpDocTag.getCpId() != null && cpDocTagService.existCpDcoTag(cpDocTag)) {
			cpDocTagService.open(cpDocTag);
			return Result.OK("修改成功");
		}
		return Result.FAILD("当前已有开放的申请");
	}


	@GetMapping("/hasopen")
	public Result hasOpen(){
		return Result.OK(cpDocTagService.existOpen());
	}

	@GetMapping("/info")
	public Result info() {
		return Result.OK(cpTeacherService.getTeacherBySession());
	}

}
