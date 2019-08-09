package com.lizhen.controll;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.lizhen.entity.Banner;
import com.lizhen.entity.User;
import com.lizhen.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserControllor {

    @Autowired
    private UserService userService;

    @Autowired
    private EchartsControllor echartsControllor;

    @RequestMapping("showUserByPage")
    public HashMap<String, Object> showUserByPage(Integer page, Integer rows) {
        System.out.println("进入Usercontrollor");
        HashMap<String, Object> map = userService.showUserByPage(page, rows);
        return map;
    }

    @RequestMapping("updateStatus")
    public HashMap<String, Object> updateStatus(String id, String status) {
        System.out.println("j进入修改户状态功能");
        User user = new User();
        user.setId(id);
        System.out.println("status" + status);
        user.setStatus(status);
        user.setStatus(status);

        HashMap<String, Object> map = userService.updateStatus(user);
        return map;
    }

    @RequestMapping("selectAllUser")

    public HashMap<String, Object> selectAllUser() {

        System.out.println("进入usercontrollor导出");

        List<User> users = userService.selectAllUser();
        for (User user : users) {
            System.out.println("查询出来的导出信息"+user);
          String pic_img = "C:\\cmfz_liz\\src\\main\\webapp\\upload\\userphoto\\"+user.getPic_img();
            System.out.println("图片路径="+pic_img);
            user.setPic_img(pic_img);
        }
        //创建输出流  从内存中写入本地磁盘
        HashMap<String, Object> map = new HashMap<>();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("持名法洲", "用户表"), User.class, users);
        try {
            workbook.write(new FileOutputStream(new File("C://Users//李震//Desktop//java//后期项目//后期项目//Day7 Poi//Test4Poi.xls")));
            workbook.close();
            map.put("success", "200");
            map.put("message", "修改成功");
        } catch (IOException e) {
            e.printStackTrace();
            map.put("success", "400");
            map.put("message", "修改失败");
        }
        return map;
    }


    //增删改
    @RequestMapping("edit")
    public String edit(User user , String oper){

        String id=null;
        //执行添加方法
        if(oper.equals("add")){
            System.out.println("执行添加方法");
             id = userService.saveUser(user);

            echartsControllor.echartsGoease();
        }
        if(oper.equals("edit")){
            System.out.println("执行修改方法");

        }
        if(oper.equals("del")){
            System.out.println("执行删除方法");

        }
        System.out.println(id);
        return id ;
    }


    //文件上传
    @RequestMapping("/uploadUser")

    public void uploadBanner(MultipartFile pic_img, String id, HttpServletRequest request) {




        System.out.println("+++++++++++++++");
        //1.获取要上传文件夹的路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/userphoto");

        //获取文件夹
        File file = new File(realPath);

        //创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }

        //获取上传文件的名字
        String filename = pic_img.getOriginalFilename();
        System.out.println("filename"+filename);
        if (!filename.equals("")){
            String name = new Date().getTime() + "-" + filename;

            //文件上传
            try {
                pic_img.transferTo(new File(realPath, name));
            } catch (IOException e) {
                e.printStackTrace();
            }

            User user = new User();
            user.setId(id);
            user.setPic_img(name);
            //做修改
            System.out.println("执行修改图片路径: " + user);
            userService.updateStatus(user);
        }
    }

}