package com.wang.music.service.impl;

import com.wang.music.dao.CollectMapper;
import com.wang.music.domain.Collect;
import com.wang.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;
    /**
     * 增加
     *
     * @param collect
     */
    @Override
    public boolean insert(Collect collect) {
        return collectMapper.insert(collect)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return collectMapper.delete(id)>0;
    }

    /**
     * 查询所有收藏
     */
    @Override
    public List<Collect> selectAll() {
        return collectMapper.selectAll();
    }

    /**
     * 查询某个用户的收藏列表
     *
     * @param userId
     */
    @Override
    public List<Collect> selectByUserId(Integer userId) {
        return collectMapper.selectByUserId(userId);
    }

    /**
     * 查询某个用户是否收藏某个歌曲
     *
     * @param userId
     * @param songId
     */
    @Override
    public boolean existSongId(Integer userId, Integer songId) {
        return collectMapper.existSongId(userId,songId)>0;
    }

    /**
     * 查询某个用户是否收藏某个歌单
     *
     * @param userId
     * @param songListId
     */
    @Override
    public boolean existSongListId(Integer userId, Integer songListId) {
        return collectMapper.existSongListId(userId, songListId)>0;
    }

    /**
     * 根据用户id和歌曲id删除
     *
     * @param userId
     * @param songId
     */
    @Override
    public boolean deleteByUserIdAndSongId(Integer userId, Integer songId) {
        return collectMapper.deleteByUserIdAndSongId(userId, songId)>0;
    }

    /**
     * 根据用户id和歌单id删除
     *
     * @param userId
     * @param songListId
     */
    @Override
    public boolean deleteByUserIdAndSongListId(Integer userId, Integer songListId) {
        return collectMapper.deleteByUserIdAndSongListId(userId, songListId)>0;
    }
}
