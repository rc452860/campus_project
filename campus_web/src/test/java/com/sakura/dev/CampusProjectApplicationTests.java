package com.sakura.dev;

import com.sakura.dev.domain.CpAcademy;
import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.repository.CpStudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CampusProjectApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Autowired
	CpStudentRepository cpStudentRepository;
	@Test
	public void testJpa(){
		CpStudent cpStudent = cpStudentRepository.findOne("110101198912071025");
		CpAcademy cpAcademy = cpStudent.getCpAcademy();
		System.out.println(cpAcademy.toString());
	}
}
