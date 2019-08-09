package com.lizhen.controll;

import com.lizhen.entity.Admin;
import com.lizhen.service.AdminService;
import com.lizhen.util.ImageCodeUtil;
import com.lizhen.util.SecurityCode;
import com.lizhen.util.SecurityImage;
import com.sun.tools.javac.jvm.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class AdminControll {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/loginadmin")
    public String login(Admin admin , Model model, String  Code, HttpSession session){
        System.out.println("logminadmin++++++++"+Code);
        String sessionCode = (String)session.getAttribute("code");
        System.out.println(sessionCode);
        if(!Code.equals(sessionCode)){
            String a="验证码输入错误";
            model.addAttribute("massage", a);
            return "/login/login";
        }else{
            try {
                System.out.println("admin+++++++");
                String adminname = admin.getAdminname();
                model.addAttribute("adminname",adminname);

                adminService.loginAdmin(admin);
                return "forward:/main/main.jsp";
            } catch (Exception e) {
              String massage= e.getMessage();
                System.out.println(massage);
                model.addAttribute("massage",massage);
                return "redirect:/login/login.jsp";
            }
        }
    }

    //Json方法
    @RequestMapping("login")
    @ResponseBody
    public HashMap<String, Object> login(String Code, String username, String password, HttpServletRequest request){

        HashMap<String, Object> map = adminService.login(Code, username, password, request);

        return map;
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.invalidate();
        return "redirect:/login/login.jsp";
    }
}
