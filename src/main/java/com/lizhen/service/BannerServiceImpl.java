package com.lizhen.service;

import com.lizhen.dao.BannerDAO;
import com.lizhen.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDAO bannerDAO;

    @Transactional(propagation = Propagation.SUPPORTS ,readOnly = true)
    @Override
    public List<Banner> showBannerByPage(Integer page, Integer rows) {
        List<Banner> banners = bannerDAO.selectByPage(page, rows);
        return banners;
    }

    @Transactional(propagation = Propagation.SUPPORTS ,readOnly = true)
    @Override
    public Integer totalCount() {
        Integer totalCount = bannerDAO.totalCount();
        return totalCount;
    }

    @Override
    public String saveBanner(Banner banner){
        String uuid = UUID.randomUUID().toString();
        banner.setId(uuid);
        banner.setStatus("1");
        Date date1 = new Date();

        banner.setUpdates(date1);
        System.out.println("service添加轮播图："+banner);
        bannerDAO.addBanner(banner);
        return uuid;
    }

    @Override
    public String eduitBanner(Banner banner) {
        String id = banner.getId();
        bannerDAO.eduitBanner(banner);

        System.out.println("要修改"+banner);
        return id;
    }

    @Override
    public void deleteBanner(String id) {
        bannerDAO.deleteBanner(id);
    }

    //修改状态方法
    @Override
    public HashMap<String, Object> updateBanner(Banner banner) {

        HashMap<String, Object> map=new HashMap<String, Object>();
        try {
         bannerDAO.eduitBanner(banner);

            map.put("success","200");
            map.put("message","修改成功");
        }catch (Exception e){
            e.getStackTrace();
            map.put("success","400");
            map.put("message","修改失败");
        }
        return map;
    }
}
