package com.lizhen.service;

import com.lizhen.entity.Album;
import com.lizhen.entity.Charpter;

import java.util.List;

public interface CharpterService {

    public List<Charpter> showCharpterByPage(String album_Id ,Integer page , Integer rows);

    public Integer totalCount();

    public String saveCharpter(Charpter charpter);

    public String eduitCharpter(Charpter charpter);

    public void deleteCharpter(String id);
}
