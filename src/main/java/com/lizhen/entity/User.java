package com.lizhen.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    @Excel(name="ID")
    private String id;
    @Excel(name="电话号码")
    private String phone;
    @Excel(name="密码")
    private String password;
    @Excel(name = "盐")
    private String salt;
    @Excel(name="头像",type = 2 ,width = 40 , height = 20,imageType = 1)
    private String pic_img;
    @Excel(name="法名")
    private String ahama;
    @Excel(name="名字")
    private String name;
    @Excel(name="性别")
    private String sex;
    @Excel(name="地址")
    private String city;
    @Excel(name="sign")
    private String sign;
    @Excel(name="状态")
    private String status;
    @Excel(name="上传日期")
    private Date reg_date;
    @Excel(name="上师ID")
    private String guruId;
    @Excel(name="月份")
    private String month;
}
