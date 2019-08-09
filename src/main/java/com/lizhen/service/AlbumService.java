package com.lizhen.service;

import com.lizhen.entity.Album;
import com.lizhen.entity.Banner;

import java.util.List;

public interface AlbumService {

    public List<Album> showAlbumByPage(Integer page , Integer rows);

    public Integer totalCount();

    public String saveAlbum(Album album);

    public String eduitAlbum(Album album);

    public void deleteAlbum( String id);




}
