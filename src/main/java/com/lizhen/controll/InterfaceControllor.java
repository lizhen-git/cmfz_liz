package com.lizhen.controll;

import com.lizhen.entity.Album;
import com.lizhen.entity.Article;
import com.lizhen.entity.Banner;
import com.lizhen.service.AlbumService;
import com.lizhen.service.ArticleService;
import com.lizhen.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController

public class InterfaceControllor {

    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("first_page")
    public HashMap<String ,Object> firstpage(String uid, String Type,String sub_type){

        System.out.println("用户ID"+uid);
        HashMap<String ,Object> maps =new HashMap<>();

        if(Type.equals("all")){
            List<Banner> banners = bannerService.showBannerByPage(1, 5);
            List<Album> albums = albumService.showAlbumByPage(1, 5);
            HashMap<String, Object> map = articleService.showArticleByPage(1, 5);
            List<Article> articles =(List<Article>)map.get("rows");
            maps.put("banner",banners);
            maps.put("albums",albums);
            maps.put("articles",articles);
        }
        if(Type.equals("wen")){
            List<Album> albums = albumService.showAlbumByPage(1, 10);
            maps.put("albums",albums);

        }
        if(Type.equals("si")){
            if(sub_type.equals("ssyj")){
                HashMap<String, Object> map = articleService.showArticleByPage(1,5);
                List<Article> articles =( List<Article>) map.get("rows");
                maps.put("articles",articles);
            }
            if(sub_type.equals("xmfy")){
                HashMap<String, Object> map = articleService.showArticleByPage(2,10);
                List<Article> articles =( List<Article>) map.get("rows");
                maps.put("articles",articles);
            }
        }
        return maps;
    }
}
