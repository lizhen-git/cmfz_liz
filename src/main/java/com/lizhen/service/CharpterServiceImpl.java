package com.lizhen.service;

import com.lizhen.dao.CharpterDAO;
import com.lizhen.entity.Album;
import com.lizhen.entity.Charpter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CharpterServiceImpl implements  CharpterService {

    @Autowired
    private CharpterDAO charpterDAO;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Charpter> showCharpterByPage(String album_Id ,Integer page, Integer rows) {

        System.out.println("分页查询service");
        List<Charpter> charpters = charpterDAO.showCharpterByPage( album_Id,page,rows);
        for (Charpter charpter : charpters) {
            System.out.println("kankan"+charpter);
        }
        return charpters;
    }
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)

    @Override
    public Integer totalCount() {
        Integer totalCount = charpterDAO.totalCount();
        return totalCount;
    }

    @Override
    public String saveCharpter(Charpter charpter) {
        String id = UUID.randomUUID().toString();
        charpter.setId(id);
        charpter.setUp_date(new Date());
        charpterDAO.saveCharpter(charpter);
        return id;
    }

    @Override
    public String eduitCharpter(Charpter charpter) {
        String id = charpter.getId();
        System.out.println("要修改的charpter"+charpter);
        charpterDAO.editCharpter(charpter);
        return id;
    }

    @Override
    public void deleteCharpter(String id) {

        charpterDAO.deleteCharpter(id);
    }
}
