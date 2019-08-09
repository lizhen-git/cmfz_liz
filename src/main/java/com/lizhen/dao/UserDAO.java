package com.lizhen.dao;

import com.lizhen.entity.City;
import com.lizhen.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO {

    public List<User> showUserByPage(@Param("start")Integer start , @Param("rows") Integer rows);

    public Integer totalCount();

    public void update(User user);

    public List<User> selectAllUser();

    public void addUser(User user);


    //男用户分布
    public List<City> selectUserByCityAndBoy();
    //女用户分布
    public List<City> selectUserByCityAndGirl();


    //男女用户的统计
    public List<City> selectUserByDateAndBoy();

    public List<City> selectUserByDateAnd();


}
