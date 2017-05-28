package com.sakura.dev;

import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.repository.CpStudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rc452 on 2017/5/27.
 */
@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    CpStudentRepository cpStudentRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.SERIALIZABLE)
    public void start() {
        System.out.println(Thread.currentThread().getId());
        try {
            File file = new File("file/resource/2009ji.xls");  // 读取Excel表格
            System.out.println(file.getAbsoluteFile());
            if(file.exists()){    // 判断文件是否存在
                Workbook workbook = WorkbookFactory.create(file);    // 通过文件创建WorkBook对象
                Sheet sheet = workbook.getSheetAt(0);    // 获取sheet 0
                DataFormatter formatter = new DataFormatter();    // 初始化单元格格式化器
                List<CpStudent> list = new ArrayList<CpStudent>();
                System.out.println(sheet.getPhysicalNumberOfRows());
                for (Row row : sheet){    // 获取行
                    if (row.getRowNum()>0){
                        CpStudent cpStudent = new CpStudent();
                        cpStudent.setCpSno(row.getCell(0).getStringCellValue());
                        cpStudent.setCpName(row.getCell(1).getStringCellValue());
                        cpStudent.setCpOldName(row.getCell(2).getStringCellValue());
                        cpStudent.setCpSex(row.getCell(3).getStringCellValue());
                        cpStudent.setCpAcademy(row.getCell(4).getStringCellValue());
                        cpStudent.setCpFaculty(row.getCell(5).getStringCellValue());
                        cpStudent.setCpIdCardNo(row.getCell(6).getStringCellValue());
                        cpStudent.setCpProfessionalName(row.getCell(7).getStringCellValue());
                        cpStudent.setCpClass(row.getCell(8).getStringCellValue());
                        cpStudent.setCpGrade(formatter.formatCellValue(row.getCell(9)));
                        cpStudent.setCpDegree(row.getCell(10).getStringCellValue());
                        cpStudent.setCpLengthOfSchool((int) row.getCell(11).getNumericCellValue());
                        /*UniversalDetector detector = new UniversalDetector(null);
                        detector.handleData(cpStudent.getCpAcademy().getBytes(),0,cpStudent.getCpAcademy().getBytes().length);
                        detector.dataEnd();
                        System.out.println(detector.getDetectedCharset());*/
                        list.add(cpStudent);
                        if (row.getRowNum()%100==0){
                            System.out.println("flush");
                            cpStudentRepository.save(list);
                            cpStudentRepository.flush();
                            list.clear();
                        }
                        if(row.getRowNum()>400){
                            throw new RuntimeException("aaa");
                        }
                    }
                }
            }else{
                System.out.println("file is not exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
    private void start2() {
        try {
            File file = new File("file/resource/2009ji.xls");  // 读取Excel表格
            System.out.println(file.getAbsoluteFile());
            if(file.exists()){    // 判断文件是否存在
                Workbook workbook = WorkbookFactory.create(file);    // 通过文件创建WorkBook对象
                Sheet sheet = workbook.getSheetAt(0);    // 获取sheet 0
                DataFormatter formatter = new DataFormatter();    // 初始化单元格格式化器
                for (Row row : sheet){    // 获取行
                    for (Cell cell:row){    // 获得单元格
                        String value = formatter.formatCellValue(cell);
                        System.out.print(String.format("|%s", StringUtils.center(value,10,"　")));
                    }
                    System.out.print("\n");
                }
            }else{
                System.out.println("file is not exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public void run(String... strings) throws Exception {
        start();
    }
}