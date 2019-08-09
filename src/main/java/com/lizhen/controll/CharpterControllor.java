package com.lizhen.controll;


import com.lizhen.entity.Album;
import com.lizhen.entity.Charpter;
import com.lizhen.service.CharpterService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioFileFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("charpter")
public class CharpterControllor {

    @Autowired
    private CharpterService charpterService;

    @RequestMapping("showCharpterByPage")
    public Map<String, Object> showCharpterByPage(Integer page, Integer rows ,String album_Id) {

        System.out.println("分页查询controllor");
        System.out.println("album_Id"+album_Id);

        List<Charpter> charpters = charpterService.showCharpterByPage( album_Id,page, rows);
        //查询总条数
        Integer totalCount = charpterService.totalCount();
        Map<String, Object> maps = new HashMap<String, Object>();
        //当前页号
        maps.put("page", page);
        //总条数
        maps.put("records", totalCount);
        //总页数
        Integer pageCount = 0;
        if (totalCount % rows == 0) {
            pageCount = totalCount / rows;
        } else {
            pageCount = totalCount / rows + 1;
        }
        maps.put("total", pageCount);
        //每页具体的数据
        maps.put("rows", charpters);
        return maps;
    }

    //增删改
    @RequestMapping("edit")
    public String edit(Charpter charpter , String oper, String album_Id){

        String id=null;
        //执行添加方法
        if(oper.equals("add")){
            System.out.println("执行添加方法");
            charpter.setAlbum_Id(album_Id);
             id = charpterService.saveCharpter(charpter);

        }
        if(oper.equals("edit")){
            System.out.println("执行修改方法");

        }
        if(oper.equals("del")){
            System.out.println("执行删除方法"+charpter.getId());
            charpterService.deleteCharpter(charpter.getId());
        }
        System.out.println(id);
        return id ;
    }

    @RequestMapping("uploadChpater")
    public void uploadChpater(MultipartFile url , String id, HttpServletRequest request){

        //获取到上传文件的lujing
        String realPath = request.getSession().getServletContext().getRealPath("upload/music");
        //获取文件夹
        File file = new File(realPath);
        //判断文件夹是否存在
        if(!file.exists()){
            file.mkdirs();
        }
        //获取上传文件的名字
        String filename = url.getOriginalFilename();
        //给文件名字加时间区别文件
       String name= new Date().getTime()+"-"+filename;
       //文件上传
        try {
            url.transferTo(new File(realPath,name));

            //获取文件大小-1
            long size = url.getSize();
            String sizes =size/1024/1024+"MB";
            //获取文件大小-2
            DecimalFormat format = new DecimalFormat("0.00");
            String str = String.valueOf(size);
            Double dd = Double.valueOf(str)/1024/1024;
            String newsizess = format.format(dd)+"MB";
            //获取文件时长
            AudioFileIO fileIO = new AudioFileIO();
            AudioFile audio = fileIO.readFile(new File(realPath, name));
            AudioHeader audioHeader = audio.getAudioHeader();
            //变成分钟数
            int length = audioHeader.getTrackLength();
            System.out.println("length"+length);
            String duration = length/60+"分"+length%60+"秒";

            Charpter charpter = new Charpter();
            charpter.setId(id);
            charpter.setUrl(name);
            charpter.setSize(newsizess);
            charpter.setDuration(duration);
            System.out.println("修改音频信息"+charpter);

            charpterService.eduitCharpter(charpter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("downloadCharpter")
    public void downloadCharpter(String fileName, HttpServletRequest request, HttpServletResponse response){

        //h获取文件路径
        String realPath = request.getSession().getServletContext().getRealPath("upload/music");
        //读取文件
        try {
            FileInputStream inputStream = new FileInputStream(new File(realPath, fileName));
            //设置相应格式 attachment:附件的形式下载 inline:在线打开
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(fileName,"utf-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream,outputStream);
            //关闭资源
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
