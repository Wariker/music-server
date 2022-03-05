package com.wang.music.service.impl;

import com.wang.music.dao.SingerMapper;
import com.wang.music.domain.Singer;
import com.wang.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerMapper singerMapper;
    /**
     * 增加
     *
     * @param singer
     */
    @Override
    public boolean insert(Singer singer) {
        return singerMapper.insert(singer)>0;
    }

    /**
     * 修改
     *
     * @param singer
     */
    @Override
    public boolean update(Singer singer) {
        return singerMapper.update(singer)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return singerMapper.delete(id)>0;
    }

    /**
     * 根据id查询整个对象
     *
     * @param id
     */
    @Override
    public Singer selectById(Integer id) {
        return singerMapper.selectById(id);
    }

    /**
     * 查询所有歌手
     */
    @Override
    public List<Singer> selectAll() {
        return singerMapper.selectAll();
    }

    /**
     * 根据歌手名字模糊查询
     *
     * @param name
     */
    @Override
    public List<Singer> selectByName(String name) {
        return singerMapper.selectByName(name);
    }

    /**
     * 根据性别查询
     *
     * @param sex
     */
    @Override
    public List<Singer> selectBySex(Integer sex) {
        return singerMapper.selectBySex(sex);
    }
}
