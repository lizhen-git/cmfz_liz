package com.lizhen.service;

import com.lizhen.dao.AdminDAO;
import com.lizhen.entity.Admin;
import com.lizhen.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public void loginAdmin(Admin admin) {

        System.out.println("service----------");
        String adminname = admin.getAdminname();
        Admin admin1 = adminDAO.selectAdmin(adminname);
        if(admin1==null) throw  new RuntimeException("此用户不存在");
        if(!admin1.getPassword().equals(admin.getPassword())) throw new RuntimeException("密码错误");
    }

    //json方法
    @Override
    public HashMap<String, Object> login(String enCode, String username, String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String imageCode = (String) session.getAttribute("code");

        HashMap<String, Object> map = new HashMap<>();
        Admin admin1 = adminDAO.selectAdmin(username);


        //判断验证码
        if(enCode.equals(imageCode)){
            //判断用户名
            if(username.equals(admin1.getAdminname())){
                //判断密码
                if(password.equals(admin1.getPassword())){

                    session.setAttribute("admin",admin1);
                    map.put("success","200");
                    map.put("message","登陆成功");
                }else{
                    map.put("success","400");
                    map.put("message","密码错误");
                }
            }else{
                map.put("success","400");
                map.put("message","用户不存在");
            }
        }else{
            map.put("success","400");
            map.put("message","验证码错误");
        }

        return map;
    }
}
