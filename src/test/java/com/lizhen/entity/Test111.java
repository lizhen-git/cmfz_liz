package com.lizhen.entity;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test111 {

    @Test
    public void test111(){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String format1 = format.format(date);
        System.out.println(format1);
        String[] split = format1.split("-");
        String nian = split[0];
        String yue = split[1];
        String ri = split[2];
        System.out.println("nian"+nian);
        System.out.println("yue"+yue);
        System.out.println("ri"+ri);
    }


    @Test
    public void test1111(){
        String b = "a";
        String s = new String("a");
        System.out.println(b);
        System.out.println(s);
        Boolean aaa = new Boolean(b=s);
        System.out.println(aaa);
    }
}
