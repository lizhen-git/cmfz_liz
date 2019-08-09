package com.lizhen.dao;

import com.lizhen.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDAO{
    public List<Album> showAlbumByPage(@Param("page") Integer page ,@Param("rows") Integer rows);

    public Integer totalCount();

    public void saveAlbum(Album album);

    public void eduitAlbum(Album album);

    public void deleteAlbum(String id);

    //查出子内容

    public List<Character> selectAllCharpter(String id);
}

