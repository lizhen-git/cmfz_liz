package com.lizhen.controll;

import com.lizhen.util.ImageCodeUtil;
import com.lizhen.util.SecurityCode;
import com.lizhen.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("code")
public class CodeContellor {

    @RequestMapping("/getCode")
    public String getCode(HttpSession session, HttpServletResponse response){

        System.out.println("code++++");
        /*String code = SecurityCode.getSecurityCode();*/
        String code = ImageCodeUtil.getSecurityCode();
        session.setAttribute("code", code);
        /*BufferedImage image = SecurityImage.createImage(code);*/
        BufferedImage image = ImageCodeUtil.createImage(code);
        ServletOutputStream stream = null;
        try {
            stream =response.getOutputStream();
            ImageIO.write(image, "png", stream);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}
