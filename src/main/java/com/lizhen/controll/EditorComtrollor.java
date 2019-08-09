package com.lizhen.controll;


import com.lizhen.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("editor")
public class EditorComtrollor {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("upload")
    public HashMap<String, Object> upload(MultipartFile articlephoto, HttpServletRequest request) {

        //获取正式路径
        String realPath = request.getSession().getServletContext().getRealPath("upload/test");
        //创建一个文件夹
        File file = new File(realPath);
        //判断这个文件夹是否存在
        if (!file.exists()) {
            file.mkdirs();
        }
        //获取文件的名字
        String filename = articlephoto.getOriginalFilename();
        //包装名字防止一样
        String name = new Date().getTime() +"-"+ filename;
        //创建HashMap返回前台响应
        HashMap<String, Object> map = new HashMap<>();

        //对成功上传后文件回显路径的封装
        //1.首先获取HTTP协议   相当于http
        String scheme = request.getScheme();
        //2.获取IP地址  相当于localhost
        String serverName = request.getServerName();
        //3.获取端口号port 相当于8989
        int serverPort = request.getServerPort();
        //4.获取当前项目名称
        String contextPath = request.getContextPath();
        //对上面获取文件的路径进行整合
        String serverPath = scheme + "://" + serverName + ":" + serverPort + "/" + contextPath + "/upload/test/" + name;        //进行文件的复制上传
        try {
            articlephoto.transferTo(new File(realPath, name));

            map.put("error", 0);
            map.put("url", serverPath);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("url", "上传失败");
        }
        return map;
    }

    @RequestMapping("queryAllPhoto")
    public HashMap<String, Object> queryAllPhoto(HttpServletRequest request) {

        HashMap<String, Object> maps = new HashMap<>();

        ArrayList<Object> lists = new ArrayList<>();
        //获取图片文件夹的路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/test");

        //获取文件夹
        File file = new File(realPath);

        //获取文件夹下所有的文件名称
        String[] names = file.list();
        for (int i = 0; i < names.length; i++) {
            //获取文件名字
            System.out.println("name"+names[i]);
            String name = names[i];

            HashMap<String, Object> map = new HashMap<>();
            map.put("is_dir",false); //是否是文件夹
            map.put("has_file",false); //是否是文件
            File file1 = new File(realPath, name);
            map.put("filesize",file1.length()); //文件的大小
            map.put("is_photo",true); //是否是图片
            String extension = FilenameUtils.getExtension(name);
            map.put("filetype",extension); //图片的格式
            map.put("filename",name); //文件的名字

            //字符串拆分   1564712632627-下载.jpg
            String[] split = name.split("-");
            String times = split[0];
            long time = Long.parseLong(times);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
            String format = dateFormat.format(time);
            map.put("datetime",format); //上传的时间

            //封装进集合
            lists.add(map);
        }

        maps.put("current_url","http://localhost:9090/cmfz/upload/test/"); //网络路径
        maps.put("total_count",lists.size());  //文件数量
        maps.put("file_list",lists);  //文件集合

        return maps;
    }

}
