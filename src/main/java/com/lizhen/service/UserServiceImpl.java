package com.lizhen.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.lizhen.dao.UserDAO;
import com.lizhen.entity.City;
import com.lizhen.entity.User;
import com.lizhen.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    @Override
    public HashMap<String, Object> showUserByPage(Integer page, Integer rows) {
        System.out.println("进入userservice");
        Integer start = (page - 1) * rows;

        System.out.println("start="+start+"rows="+rows);
        List<User> users = userDAO.showUserByPage(start, rows);
        System.out.println("-----------");
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("长度" + users.size());
        Integer totalCount = userDAO.totalCount();
        //计算总的页数
        Integer pageCount = totalCount / rows == 0 ? totalCount / rows : totalCount / rows + 1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", pageCount);
        map.put("records", totalCount);
        map.put("page", page);
        map.put("rows", users);
        return map;
    }

    @Override
    public HashMap<String, Object> updateStatus(User user) {
        HashMap<String , Object> map = new HashMap<>();

        try {

            userDAO.update(user);
            map.put("success","200");
            map.put("message","修改成功");
        }catch (Exception e){
            e.getStackTrace();
            map.put("success","400");
            map.put("message","修改失败");
        }
        return map;
    }

    @Override
    public List<User> selectAllUser() {
        System.out.println("查询所有用户并导出");
        List<User> users = userDAO.selectAllUser();
        return users;
    }

    @Override
    public String saveUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        String salt = Md5Utils.getSalt(5);
        String saltPassword = salt+user.getPassword();
        String newPassword = Md5Utils.getMd5Code(saltPassword);
        user.setPassword(newPassword);
        user.setStatus("1");
        Date date = new Date();
        user.setReg_date(date);
        //注册月份
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        String[] split = format1.split("-");
        String yue = split[1];
        System.out.println("format"+format1);
        System.out.println("yue"+yue);
        user.setMonth(yue);

        user.setSalt(salt);
        user.setGuruId("1");
        user.setSign("1");
        System.out.println("增加User="+user);
        userDAO.addUser(user);
        return id;

    }

    @Override
    public List<City> selectUserByCityAndBoy() {
        System.out.println("进入查询男用户Service");
        List<City> cities1 = userDAO.selectUserByCityAndBoy();

        return cities1;
    }

    @Override
    public List<City> selectUserByCityAndGirl() {
        System.out.println("进入查询女用户Service");
        List<City> cities2 = userDAO.selectUserByCityAndGirl();
        return cities2;
    }

    @Override
    public List<City> selectUserByDateAndBoy() {
        System.out.println("进入统计男用户Service");
        List<City> cities1 = userDAO.selectUserByDateAndBoy();
        return cities1;
    }

    @Override
    public List<City> selectUserByDateAndGirl() {
        System.out.println("进入统计女用户Service");
        List<City> cities2 = userDAO.selectUserByDateAnd();
        return cities2;
    }


}
