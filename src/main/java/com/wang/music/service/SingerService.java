package com.wang.music.service;

import com.wang.music.domain.Singer;

import java.util.List;

public interface SingerService {
    /**
     * 增加
     */
    public boolean insert(Singer singer);

    /**
     * 修改
     */
    public boolean update(Singer singer);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据id查询整个对象
     */
    public Singer selectById(Integer id);

    /**
     * 查询所有歌手
     */
    public List<Singer> selectAll();

    /**
     * 根据歌手名字模糊查询
     */
    public List<Singer> selectByName(String name);

    /**
     * 根据性别查询
     */
    public List<Singer> selectBySex(Integer sex);
}
