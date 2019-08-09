package com.lizhen.entity;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class testInputExcle {

    @Test
    public void testInputExcle() {
        try {
            //获取要导入的文件
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("C://Users//李震//Desktop//java//后期项目//后期项目//Day7 Poi//Test2Poi.xls")));

            //获取工作薄
            HSSFSheet sheet = workbook.getSheet("用户信息表1");

            for (int i = 3; i <= sheet.getLastRowNum(); i++) {

                Student student = new Student();

                //获取行
                HSSFRow row = sheet.getRow(i);

                //获取Id
                student.setId(row.getCell(0).getStringCellValue());
                //获取name
                student.setName(row.getCell(1).getStringCellValue());
                //获取age
                double ages = row.getCell(2).getNumericCellValue();
                student.setAge((int) ages);
                //获取生日
                student.setBir(row.getCell(3).getDateCellValue());

                //调用一个插入数据库的方法
                System.out.println(student);
            }

            //关闭资源
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
