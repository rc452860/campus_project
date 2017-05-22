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
            File file = new File("file/resource/2009ji.xls");
            System.out.println(file.getAbsoluteFile());
            if(file.exists()){
                Workbook workbook = WorkbookFactory.create(file);
                Sheet sheet = workbook.getSheetAt(0);
                DataFormatter formatter = new DataFormatter();
                for (Row row : sheet){
                    for (Cell cell:row){
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
