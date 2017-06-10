package com.sakura.dev;

import com.sakura.dev.domain.*;
import com.sakura.dev.repository.*;
import com.sakura.dev.service.CpAcademyService;
import com.sakura.dev.service.CpStudentService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by rc452 on 2017/5/27.
 */
@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    CpStudentService cpStudentService;
    @Autowired
    CpAcademyService cpAcademyService;


    Map<String,CpAcademy> academyHashMap =new HashMap<>();


    /**
     * 0学号	1姓名	2曾用名	3性别	4学院	5系	6身份证号	7专业名称	8行政班	9年级	10层次	11学制
     */
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
                List<CpStudent> list = new ArrayList<CpStudent>();
                System.out.println(sheet.getPhysicalNumberOfRows());
                long start = System.currentTimeMillis();
                for (Row row :sheet){
                    if (row.getRowNum()>0){
                        try {
                            this.anylizeAcademy(row);
                            this.anylizeStudentInfo(row);
                        }
                        catch (DataIntegrityViolationException e){
//                            System.out.println(e.getMessage());
//                            System.out.println(e.getRootCause());
                            continue;
                        }
                    }
                }
                long end = System.currentTimeMillis();
                System.out.println(String.format("耗时：%s",end-start));
            }else{
                System.out.println("file is not exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分析学院和专业
     * @param row
     */
    @Transactional
    public void anylizeAcademy(Row row){
        DataFormatter dataFormatter = new DataFormatter();
        //学院
        String f = row.getCell(4).getStringCellValue();
        CpAcademy academy = academyHashMap.computeIfAbsent(
                f,
                (String k)->{
                    CpAcademy temp =  new CpAcademy(k,1,null,0,null);
                    temp = cpAcademyService.getOrCreate(temp);
                    return temp;
                }
        );
        //专业
        f = row.getCell(7).getStringCellValue();
        CpAcademy major = academyHashMap.computeIfAbsent(
                f,
                (String k)->{
                    CpAcademy temp = new CpAcademy(k,
                            2,
                            row.getCell(10).getStringCellValue(),
                            (int) row.getCell(11).getNumericCellValue(),
                            academy);
                    temp = cpAcademyService.getOrCreate(temp);
                    return temp;

                }
        );
        //班级
        f = row.getCell(8).getStringCellValue();
        CpAcademy cpAcademy = academyHashMap.computeIfAbsent(
                f,
                (String k)->{
                    CpAcademy temp = new CpAcademy(
                            k,
                            3,
                            row.getCell(10).getStringCellValue(),
                            (int) row.getCell(11).getNumericCellValue(),
                            major,
                            dataFormatter.formatCellValue(row.getCell(9))
                    );
                    temp = cpAcademyService.getOrCreate(temp);
                    return temp;
                }
        );
    }

    public void anylizeStudentInfo(Row row){

        CpStudent cpStudent = new CpStudent();
        cpStudent.setCpSno(row.getCell(0).getStringCellValue());
        cpStudent.setCpName(row.getCell(1).getStringCellValue());
        cpStudent.setCpOldName(row.getCell(2).getStringCellValue());
        cpStudent.setCpSex(row.getCell(3).getStringCellValue());
        cpStudent.setCpIdCardNo(row.getCell(6).getStringCellValue());
        //学院
        String f = row.getCell(4).getStringCellValue();
        CpAcademy cpAcademy = academyHashMap.get(f);
        cpStudent.setCpAcademy(cpAcademy);
        //专业
        f = row.getCell(7).getStringCellValue();
        CpAcademy cpMajor = academyHashMap.get(f);
        cpStudent.setCpMajor(cpMajor);
        //班级
        f = row.getCell(8).getStringCellValue();
        CpAcademy cpClass = academyHashMap.get(f);
        cpStudent.setCpClass(cpClass);
        cpStudentService.getOrCreate(cpStudent);
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