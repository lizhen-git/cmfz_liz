package com.lizhen.entity;

public class Admin {
    private String  id ;
    private String adminname;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(String id, String adminname, String password) {
        this.id = id;
        this.adminname = adminname;
        this.password = password;
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", adminname='" + adminname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
