package com.lizhen.controll;

import com.lizhen.entity.Article;
import com.lizhen.entity.Banner;
import com.lizhen.entity.User;
import com.lizhen.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("article")
public class ArticleControllor {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("showArticleBypage")
    public HashMap<String, Object> showArticleBypage(Integer page, Integer rows) {

        System.out.println("进入articlecontrollor");
        HashMap<String, Object> map = articleService.showArticleByPage(page, rows);
        return map;
    }

    //只修改文章的状态功能
    @RequestMapping("updateStatus")
    public HashMap<String, Object> updateStatus(String id, String status) {
        System.out.println("j进入修改状态功能");
        Article article = new Article();
        article.setId(id);
        System.out.println("status" + status);
        article.setStatus(status);
        article.setStatus(status);

        HashMap<String, Object> map = articleService.updateStatus(article);
        return map;
    }


    //模态框修改文章标题和文章的内容
    @RequestMapping("update")
    public HashMap<String, Object> update(Article article, String ArticleId) {
        article.setId(ArticleId);
        System.out.println(article);
        HashMap<String, Object> map = articleService.updateStatus(article);
        return map;
    }

    //模态框添加文章的功能
    @RequestMapping("add")
    public HashMap<String, Object> add(Article article) {
        System.out.println("增加+" + article);
        article.setId(UUID.randomUUID().toString());
        article.setUp_date(new Date());
        article.setStatus("1");
        System.out.println("完成" + article);
        HashMap<String, Object> map = articleService.saveArticle(article);
        return map;
    }

    //只修改该文章封面的功能，为了上传封面
    @RequestMapping("edit")
    public String edit(Article article, String oper) {

        String id = null;
        //执行添加方法
        if (oper.equals("edit")) {
            System.out.println("执行添加方法");
            id = articleService.updateStatus1(article);
        }
        return id;
    }


    //文件上传，修改文章封面
    @RequestMapping("/uploadArticle")

    public void uploadArticle(MultipartFile insert_img, String id, HttpServletRequest request) {
        System.out.println("+++++++++++++++");
        //1.获取要上传文件夹的路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/article");

        //获取文件夹
        File file = new File(realPath);
        //创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
        //获取上传文件的名字
        String filename = insert_img.getOriginalFilename();
        System.out.println("filename" + filename);
        if (!filename.equals("")) {
            String name = new Date().getTime() + "-" + filename;
            //文件上传
            try {
                insert_img.transferTo(new File(realPath, name));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Article article = new Article();
            article.setId(id);
            article.setInsert_img(name);
            //做修改
            System.out.println("执行修改图片路径: " + article);
            articleService.updateStatus(article);
        }
    }

    //删除文章的功能
    @RequestMapping("deleteArticle")
    public HashMap<String, Object> deleteArticle(Article article, String ArticleId) {
        System.out.println(ArticleId);
        HashMap<String, Object> map = articleService.deleteArticle(ArticleId);
        return map;
    }
}
