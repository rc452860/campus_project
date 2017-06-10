package com.sakura.dev.controller;

import com.sakura.dev.controller.dto.Result;
import com.sakura.dev.domain.CpAcademy;
import com.sakura.dev.service.CpAcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rc452 on 2017/6/8.
 */
@RestController
@RequestMapping("/academy")
public class AcademyController {
	@Autowired
	CpAcademyService cpAcademyService;
	@GetMapping
	public Result getAcademy(String name,int rank){
		List<CpAcademy> list = cpAcademyService.getAcademyByName(name,rank);
		if (list==null){
			return Result.OK(new ArrayList<CpAcademy>());
		}else{
			return Result.OK(list);
		}
	}
	@GetMapping("/parent")
	public Result getParent(Long id){
		CpAcademy cpAcademy = cpAcademyService.getParentById(id);
		return Result.OK(cpAcademy);
	}
}
