package com.lizhen.entity;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestExcel {

    @Test
    public void testExcel(){

        //添加一个工作文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //添加一个工作簿
        HSSFSheet sheet = workbook.createSheet("学生信息");
        //添加一行(下标重0开始)
        HSSFRow row = sheet.createRow(0);
        //添加（单元格）下标也是重0开始
        HSSFCell cell = row.createCell(0);

        /*//合并列
        Cell cell1 = row.createCell(0);
        //要合并的列      参数：行开始，行结束，列开时，列结束
        CellRangeAddress region=new CellRangeAddress(0, 0, 0, 3);
        sheet.addMergedRegion(region);
        cell1.setCellValue("用户数据");*/

        //合并行
        Cell cell1=row.createCell(6);
        CellRangeAddress region=new CellRangeAddress(0, 5, 6, 6);
        sheet.addMergedRegion(region);
        cell1.setCellValue("用户数据");

        //导出单元格
        try {
            workbook.write(new FileOutputStream(new File("C://Users//李震//Desktop//java//后期项目//后期项目//Day7 Poi//TestPoi.xls")));
            //释放资源
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
