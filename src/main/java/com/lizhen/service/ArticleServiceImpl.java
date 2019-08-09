package com.lizhen.service;

import com.lizhen.dao.ArticleDAO;
import com.lizhen.entity.Article;
import com.lizhen.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements  ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    //分页查询文章，展示文章
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public HashMap<String, Object> showArticleByPage(Integer page, Integer rows) {

        System.out.println("进入userservice");
        Integer start = (page - 1) * rows;

        System.out.println("start="+start+"rows="+rows);
        List<Article> articles = articleDAO.showArticleByPage(start, rows);
        System.out.println("-----------");
        for (Article user : articles) {
            System.out.println(user);
        }
        System.out.println("长度" + articles.size());
        Integer totalCount = articleDAO.totalCount();
        //计算总的页数
        Integer pageCount = totalCount / rows == 0 ? totalCount / rows : totalCount / rows + 1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", pageCount);
        map.put("records", totalCount);
        map.put("page", page);
        map.put("rows", articles);
        return map;
    }

    //修改文章的状态
    @Override
    public HashMap<String, Object> updateStatus(Article article) {
        HashMap<String , Object> map = new HashMap<>();

        try {
            System.out.println("art++upload"+article);
            articleDAO.update(article);
            map.put("success","200");
            map.put("message","修改成功");
        }catch (Exception e){
            e.getStackTrace();
            map.put("success","400");
            map.put("message","修改失败");
        }
        return map;
    }

    //增加文章的功能
    @Override
    public HashMap<String, Object> saveArticle(Article article) {
        HashMap<String , Object> map = new HashMap<>();

        try {

            articleDAO.saveArticle(article);
            map.put("success","200");
            map.put("message","修改成功");
        }catch (Exception e){
            e.getStackTrace();
            map.put("success","400");
            map.put("message","修改失败");
        }
        return map;
    }

    //修改或者添加封面
    @Override
    public String updateStatus1(Article article) {

        System.out.println("进入updateStatus1修改图片");
        String id = article.getId();
        articleDAO.update(article);
            return id;
    }

    //删除文章
    @Override
    public HashMap<String, Object> deleteArticle(String id) {
        System.out.println("进入删除功能");
        HashMap<String , Object> map = new HashMap<>();

        try {
            System.out.println("进入删除dao"+id);
            articleDAO.deleteArticle(id);
            map.put("success","200");
            map.put("message","修改成功");
        }catch (Exception e){
            e.getStackTrace();
            map.put("success","400");
            map.put("message","修改失败");
        }
        return map;
    }
}
