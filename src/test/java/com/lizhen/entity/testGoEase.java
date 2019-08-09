package com.lizhen.entity;

import io.goeasy.GoEasy;
import org.junit.Test;

public class testGoEase {
    @Test
    public void test111(){
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-ca83f7886db74d4dba645bb64918eac5");

        //参数: 管道(标识)名称,发布的内容
        goEasy.publish("myChannellizhen", "Hello, 158 GoEasy!");
    }


}
