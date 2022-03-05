package com.wang.music.dao;

import com.wang.music.domain.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerMapper {
    /**
     * 增加
     */
    public int insert(Singer singer);

    /**
     * 修改
     */
    public int update(Singer singer);

    /**
     * 删除
     */
    public int delete(Integer id);

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
