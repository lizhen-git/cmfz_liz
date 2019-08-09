package com.lizhen.dao;

import com.lizhen.entity.Banner;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.EAN;

import java.util.List;

public interface BannerDAO {
    public List<Banner> selectByPage(@Param("page") Integer page, @Param("rows") Integer rows);


    public Integer totalCount();

    public void addBanner(Banner banner);

    public void eduitBanner(Banner banner);

    public void deleteBanner(String id);


}
