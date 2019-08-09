package com.lizhen.service;

import com.lizhen.entity.City;
import com.lizhen.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    public HashMap<String ,Object> showUserByPage(Integer page , Integer rows);


    public HashMap<String ,Object> updateStatus(User user);

    public List<User> selectAllUser();

    public String saveUser(User user);




    //查询用户分布的两个方法
    public List<City> selectUserByCityAndBoy();

    public List<City> selectUserByCityAndGirl();


    //统计用户注册月份人数的两个方法
    public List<City> selectUserByDateAndBoy();

    public List<City> selectUserByDateAndGirl();

}
