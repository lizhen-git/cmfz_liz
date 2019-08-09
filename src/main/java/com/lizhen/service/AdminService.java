package com.lizhen.service;

import com.lizhen.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface AdminService {
    public void loginAdmin(Admin admin);

    public HashMap<String, Object> login(String enCode, String username, String password, HttpServletRequest request);
}
