package com.sakura.dev;

import com.sakura.dev.domain.CpAcademy;
import com.sakura.dev.domain.CpApplyInfo;
import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.repository.CpStudentRepository;
import com.sakura.dev.service.CpApplyInfoService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CampusProjectApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Autowired
	CpStudentRepository cpStudentRepository;

	@Autowired
	CpApplyInfoService cpApplyInfoService;
	@Test
	public void testJpa(){
		CpStudent cpStudent = cpStudentRepository.findOne("110101198912071025");
		CpAcademy cpAcademy = cpStudent.getCpAcademy();
		System.out.println(cpAcademy.toString());
	}

	@Test
	public void testExistOpen() throws ParseException {
		cpApplyInfoService.open(new CpApplyInfo("aa", DateUtils.parseDate("2017-06-13","yyyy-MM-dd"),DateUtils.parseDate("2017-06-15","yyyy-MM-dd")));
		Assert.assertFalse(cpApplyInfoService.existOpen());
	}
}
