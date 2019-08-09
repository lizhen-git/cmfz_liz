package com.lizhen.entity;

import com.alibaba.fastjson.JSONObject;
import io.goeasy.GoEasy;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class testechartsgoease {
    @Test
    public void oo(){
        //发布消息  发布地址，appkey
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-ca83f7886db74d4dba645bb64918eac5");

        //参数: 管道(标识)名称,发布的内容
        goEasy.publish("myChannellizhen", "Hello, 158 GoEasy!");
    }

    @Test
    public void TestGoEasyEcharts(){

        for (int i = 0; i < 10; i++) {

            //获取随机数
            Random random = new Random();
            random.nextInt(10);

            //将随机数放入数组
            int[] randoms ={random.nextInt(500),random.nextInt(100),random.nextInt(900),random.nextInt(400),random.nextInt(300),random.nextInt(800)};

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("month", Arrays.asList("1月","2月","3月","4月","5月","6月"));
            jsonObject.put("boys",randoms);
            jsonObject.put("girls",randoms);

            //将对象转为json格式字符串
            String content = jsonObject.toJSONString();

            //发布消息  发布地址，appkey
            GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-ca83f7886db74d4dba645bb64918eac5");

            //参数: 管道(标识)名称,发布的内容
            goEasy.publish("myChannellizhen", content);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
