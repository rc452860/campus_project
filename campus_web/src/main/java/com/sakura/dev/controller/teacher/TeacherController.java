package com.sakura.dev.controller.teacher;

import com.sakura.dev.controller.dto.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rc452 on 2017/6/8.
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
	@PostMapping
	public Result login(){
		return Result.OK("登陆失败，请检查用户名或密码");
	}
}
