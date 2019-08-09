package com.lizhen.entity;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class testPoi {

    @Test
    public void testPoi()throws Exception{
        List<User1> user1s = new ArrayList<>();
        user1s.add(new User1("1", "小黄", 23,new Date()));
        user1s.add(new User1("2", "小刘", 26, new Date()));
        user1s.add(new User1("3", "小黑", 24, new Date()));
        user1s.add(new User1("4", "小张", 18, new Date()));

        //参数：标题，表名，实体类类对象，导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生","学生"),User1.class, user1s);

        workbook.write(new FileOutputStream(new File("C://Users//李震//Desktop//java//后期项目//后期项目//Day7 Poi//Test3Poi.xls")));
        workbook.close();
    }
}
