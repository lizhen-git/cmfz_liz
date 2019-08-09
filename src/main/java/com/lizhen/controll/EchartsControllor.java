package com.lizhen.controll;

import com.alibaba.fastjson.JSONObject;
import com.lizhen.entity.City;
import com.lizhen.entity.Pro;
import com.lizhen.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("echarts")
public class EchartsControllor {

    @Autowired
    private UserService userService;

    @RequestMapping("queryUserByMonthAndSex")
    public HashMap<String, Object> queryUserByMonthAndSex(){

        HashMap<String, Object> map = new HashMap<>();

        List boys = new ArrayList();
        List girls = new ArrayList();
        TreeSet   month = new TreeSet();
        List<City> cities1 = userService.selectUserByDateAndBoy();
        for (City city : cities1) {
            System.out.println("++++"+city);
            month.add(city.getName());


        }

        List<City> cities2 = userService.selectUserByDateAndGirl();
        for (City city : cities2) {
            System.out.println("-------"+city);
            month.add(city.getName());

        }

        //增加男用户
       a: for ( Object o : month) {
           int count =0;
           b:    for (City city : cities1) {
                count++;
                String s = (String)o;
                if(s.equals(city.getName())){

                    boys.add(city.getValue());break ;

                }else {
                    if (count==cities1.size())
                        boys.add(0);
                }
            }
        }

        //增加女用户
        for ( Object o : month) {
            int count1 =0;
                  for (City city : cities2) {
                count1++;
            String s = (String)o;
                if(s.equals(city.getName())){
                    girls.add(city.getValue());break ;
                }else{
                    if(count1==cities2.size()) {
                        girls.add(0);
                    }
                }
            }
        }

        for (Object o : month) {
            System.out.println("month"+o);
        }

        for (Object boy : boys) {
            System.out.println("boy"+boy);
        }

        for (Object girl : girls) {
            System.out.println("girl"+girl);
        }

        map.put("month",month);
        map.put("boys",boys);
        map.put("girls",girls);
        return map;
    }

    @RequestMapping("queryUserByCityAndSex")
    public List<Pro> queryUserByCityAndSex(){

        //封装 city 集合
        List<City> boys = new ArrayList<>();

        List<City> cities1 = userService.selectUserByCityAndBoy();
        for (City city : cities1) {
            System.out.println("citynan="+city);
            boys.add(city);
        }

        //封装 city 集合
        List<City> girls = new ArrayList<>();
        List<City> cities2 = userService.selectUserByCityAndGirl();
        for (City city1 : cities2) {
            System.out.println("citynv="+city1);
            girls.add(city1);
        }
        Pro pro1 = new Pro("女用户",girls);
        Pro pro2 = new Pro("男用户",boys);

        //封装  最外层集合
        List<Pro> pros = new ArrayList<>();
        pros.add(pro1);
        pros.add(pro2);
        return pros;
    }


    //为了实现goease实时传输数据

    public void echartsGoease(){

        HashMap<String, Object> map = new HashMap<>();

        List boys = new ArrayList();
        List girls = new ArrayList();
        TreeSet   month = new TreeSet();
        List<City> cities1 = userService.selectUserByDateAndBoy();
        for (City city : cities1) {
            System.out.println("++++"+city);
            month.add(city.getName());


        }

        List<City> cities2 = userService.selectUserByDateAndGirl();
        for (City city : cities2) {
            System.out.println("-------"+city);
            month.add(city.getName());

        }

        //增加男用户
        a: for ( Object o : month) {
            int count =0;
            b:    for (City city : cities1) {
                count++;
                String s = (String)o;
                if(s.equals(city.getName())){

                    boys.add(city.getValue());break ;

                }else {
                    if (count==cities1.size())
                        boys.add(0);
                }
            }
        }

        //增加女用户
        for ( Object o : month) {
            int count1 =0;
            for (City city : cities2) {
                count1++;
                String s = (String)o;
                if(s.equals(city.getName())){
                    girls.add(city.getValue());break ;
                }else{
                    if(count1==cities2.size()) {
                        girls.add(0);
                    }
                }
            }
        }

        for (Object o : month) {
            System.out.println("month"+o);
        }

        for (Object boy : boys) {
            System.out.println("boy"+boy);
        }

        for (Object girl : girls) {
            System.out.println("girl"+girl);
        }



        JSONObject jsonObject = new JSONObject();
        jsonObject.put("month",month);
        jsonObject.put("boys",boys);
        jsonObject.put("girls",girls);

        //将对象转为json格式字符串
        String content = jsonObject.toJSONString();

        //发布消息  发布地址，appkey
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-ca83f7886db74d4dba645bb64918eac5");

        //参数: 管道(标识)名称,发布的内容
        goEasy.publish("myChannellizhen", content);

    }
}
