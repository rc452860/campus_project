package com.sakura.dev;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
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
}
