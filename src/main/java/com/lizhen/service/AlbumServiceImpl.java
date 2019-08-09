package com.lizhen.service;

import com.lizhen.dao.AlbumDAO;
import com.lizhen.entity.Album;
import com.lizhen.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDAO albumDAO;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Album> showAlbumByPage(Integer page, Integer rows) {

        List<Album> albums = albumDAO.showAlbumByPage(page, rows);
        return albums;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Integer totalCount() {
        Integer totalCount = albumDAO.totalCount();
        return totalCount;
    }

    @Override
    public String saveAlbum(Album album) {
        String uid = UUID.randomUUID().toString();
        album.setId(uid);
        album.setCounts(5);
        album.setPub_date(new Date());
        album.setScore(5);
        System.out.println("添加专辑表"+album);
        albumDAO.saveAlbum(album);
        return  uid;
    }

    @Override
    public String eduitAlbum(Album album) {

        String id = album.getId();
        albumDAO.eduitAlbum(album);
        System.out.println("要修改的"+album);
        return id;
    }

    @Override
    public void deleteAlbum(String id) {
        System.out.println("删除前的检查有无子内容");
        List<Character> characters = albumDAO.selectAllCharpter(id);
        if(characters.size()!=0){
            throw new RuntimeException("不可删除");
        }else{
            System.out.println("现在可以删除本专辑"+id);
            albumDAO.deleteAlbum(id);
        }
    }
}
