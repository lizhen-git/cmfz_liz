package com.lizhen.controll;

import com.lizhen.entity.Banner;
import com.lizhen.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/banner")
public class BannerControllor {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/showBannerByPage")
    @ResponseBody
    public Map<String ,Object> showBannerByPage(Integer page, Integer rows)throws Exception{
        //当前页的数据
        List<Banner> banners = bannerService.showBannerByPage(page,rows);
        //查询总条数
        Integer totalCount = bannerService.totalCount();
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
        maps.put("rows",banners);
        return   maps;
    }

    //增删改
    @RequestMapping("edit")
    public String edit(Banner banner , String oper){

        String id=null;
        //执行添加方法
        if(oper.equals("add")){
            System.out.println("执行添加方法");
             id = bannerService.saveBanner(banner);
        }
        if(oper.equals("edit")){
            System.out.println("执行修改方法");
             id = bannerService.eduitBanner(banner);
        }
        if(oper.equals("del")){
            System.out.println("执行删除方法"+banner);
            bannerService.deleteBanner(banner.getId());
        }
        System.out.println(id);
        return id ;
    }


    //文件上传
    @RequestMapping("/uploadBanner")

    public void uploadBanner(MultipartFile imgpath, String id, HttpServletRequest request) {




        System.out.println("+++++++++++++++");
        //1.获取要上传文件夹的路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/photo");

        //获取文件夹
        File file = new File(realPath);

        //创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }

        //获取上传文件的名字
        String filename = imgpath.getOriginalFilename();
        System.out.println("filename"+filename);
        if (!filename.equals("")){
            String name = new Date().getTime() + "-" + filename;

            //文件上传
            try {
                imgpath.transferTo(new File(realPath, name));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Banner banner = new Banner();
            banner.setId(id);
            banner.setImgpath(name);
            //做修改
            System.out.println("执行修改图片路径: " + banner);
            bannerService.eduitBanner(banner);
        }
    }


    //修改状态方法
    @RequestMapping("updateStatus")
    public HashMap<String, Object> updateStatus(String id,String status){

        Banner banner = new Banner();
        banner.setId(id);
        banner.setStatus(status);

        HashMap<String, Object> map = bannerService.updateBanner(banner);
        return map;
    }
}
