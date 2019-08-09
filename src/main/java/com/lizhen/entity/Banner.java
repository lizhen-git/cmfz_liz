package com.lizhen.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.regex.Pattern;

public class Banner {
    private String id;
    private String title;
    private String imgpath;
    private String description;
    private String status;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdates() {
        return updates;
    }

    public void setUpdates(Date updates) {
        this.updates = updates;
    }

    public Banner(String id, String title, String imgpath, String description, String status, Date updates) {
        this.id = id;
        this.title = title;
        this.imgpath = imgpath;
        this.description = description;
        this.status = status;
        this.updates = updates;
    }

    public Banner() {
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", imgpath='" + imgpath + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", updates=" + updates +
                '}';
    }
}
