package com.lizhen.service;

import com.lizhen.entity.Article;
import com.lizhen.entity.User;

import java.util.HashMap;

public interface ArticleService {

    public HashMap<String ,Object> showArticleByPage(Integer page , Integer rows);


    public HashMap<String ,Object> updateStatus(Article article);

    public HashMap<String ,Object> saveArticle(Article article);

    public String updateStatus1(Article article);

    public HashMap<String ,Object> deleteArticle(String id );
}
