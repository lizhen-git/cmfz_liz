package com.lizhen.service;

import com.lizhen.entity.Banner;

import java.util.HashMap;
import java.util.List;

public interface BannerService {

    public List<Banner> showBannerByPage(Integer page,Integer rows);

    public Integer totalCount();

    public String saveBanner(Banner banner);

    public String eduitBanner(Banner banner);

    public void deleteBanner(String id);

    public HashMap<String, Object> updateBanner(Banner banner);


}
