package com.lizhen.dao;

import com.lizhen.entity.Article;
import com.lizhen.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDAO {

    public List<Article> showArticleByPage(@Param("start")Integer start , @Param("rows") Integer rows);

    public Integer totalCount();


    public void update(Article article);

    public void saveArticle(Article article);

    public void deleteArticle(String id );
}
