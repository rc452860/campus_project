package com.sakura.dev.controller.teacher;

import com.sakura.dev.controller.dto.LoginRequest;
import com.sakura.dev.controller.dto.Result;
import com.sakura.dev.domain.CpApplyInfo;
import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.domain.CpTeacher;
import com.sakura.dev.service.CpApplyInfoService;
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
	CpApplyInfoService cpApplyInfoService;

	@Autowired
	HttpSession session;

	@PostMapping("/login")
	public Result login(@RequestBody LoginRequest loginRequest){
		CpTeacher cpTeacher = cpTeacherService.login(loginRequest);
		if (cpTeacher!=null){
			session.setAttribute("student",cpTeacher);
			return Result.OK(cpTeacher);
		}
		return Result.FAILD("登陆失败");
	}

	@PostMapping("/open")
	public Result open(@RequestBody CpApplyInfo cpApplyInfo){
		if (cpApplyInfoService.existOpen()){
			cpApplyInfoService.open(cpApplyInfo);
			return Result.OK("开放成功");
		}
		return Result.FAILD("时间区间冲突，已在开放时间区间");
	}

	@GetMapping("/hasopen")
	public Result hasOpen(){
		return Result.OK(cpApplyInfoService.existOpen());
	}

}
