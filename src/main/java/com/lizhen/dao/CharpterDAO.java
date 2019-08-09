package com.lizhen.dao;

import com.lizhen.entity.Charpter;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface CharpterDAO {

    public List<Charpter> showCharpterByPage(@Param("album_Id") String album_Id   , @Param("page") Integer page , @Param("rows") Integer rows);

    public Integer totalCount();

    public void saveCharpter(Charpter charpter);

    public void editCharpter(Charpter charpter);

    public void deleteCharpter(String id);
}
