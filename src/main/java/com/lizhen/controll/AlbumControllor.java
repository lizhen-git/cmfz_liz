package com.lizhen.controll;

import com.lizhen.entity.Album;
import com.lizhen.entity.Banner;
import com.lizhen.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumControllor {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("showAlbumByPage")
    public Map<String ,Object> showAlbumByPage(Integer page,Integer rows){

        List<Album> albums= albumService.showAlbumByPage(page, rows);
        //查询总条数
        Integer totalCount = albumService.totalCount();
        Map<String,Object> maps=new HashMap<String,Object>();
        //当前页号
        maps.put("page",page);
        //总条数
        maps.put("records",totalCount);
        //总页数
        Integer  pageCount=0;
        if (totalCount%rows==0){
            pageCount=totalCount/rows;
        }else {
            pageCount=totalCount/rows+1;
        }
        maps.put("total",pageCount);
        //每页具体的数据
        maps.put("rows",albums);
        return   maps;
    }

    //增删改
    @RequestMapping("edit")
    public String edit(Album album , String oper){

        String id=null;
        //执行添加方法
        if(oper.equals("add")){
            System.out.println("执行添加方法");
            id = albumService.saveAlbum(album);
        }
        if(oper.equals("edit")){
            System.out.println("执行修改方法");
             id = albumService.eduitAlbum(album);
        }
        if(oper.equals("del")){
            System.out.println("执行删除方法");
                albumService.deleteAlbum(album.getId());
        }
        System.out.println(id);
        return id ;
    }

    //文件上传
    @RequestMapping("/uploadAlbum")

    public void uploadBanner(MultipartFile cover_img, String id, HttpServletRequest request) {




        System.out.println("+++++++++++++++");
        //1.获取要上传文件夹的路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/album");

        //获取文件夹
        File file = new File(realPath);

        //创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }

        //获取上传文件的名字
        String filename = cover_img.getOriginalFilename();
        System.out.println("filename"+filename);
        if (!filename.equals("")){
            String name = new Date().getTime() + "-" + filename;

            //文件上传
            try {
                cover_img.transferTo(new File(realPath, name));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Album album = new Album();
            album.setId(id);
            album.setCover_img(name);
            //做修改
            System.out.println("执行修改图片路径: " + album);
            albumService.eduitAlbum(album);
        }
    }
 }
