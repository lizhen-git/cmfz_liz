package com.lizhen.entity;

import java.util.Date;

public class Charpter {

    private String id ;
    private String url;
    private String size;
    private String duration;
    private Date up_date;
    private  String album_Id;

    private Album album;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    public String getAlbum_Id() {
        return album_Id;
    }

    public void setAlbum_Id(String album_Id) {
        this.album_Id = album_Id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Charpter(String id, String url, String size, String duration, Date up_date, String album_Id, Album album) {
        this.id = id;
        this.url = url;
        this.size = size;
        this.duration = duration;
        this.up_date = up_date;
        this.album_Id = album_Id;
        this.album = album;
    }

    public Charpter() {
    }

    @Override
    public String toString() {
        return "Charpter{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", size='" + size + '\'' +
                ", duration='" + duration + '\'' +
                ", up_date=" + up_date +
                ", album_Id='" + album_Id + '\'' +
                ", album=" + album +
                '}';
    }
}
