package com.wang.music.service;

import com.wang.music.domain.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectService {
    /**
     * 增加
     */
    public boolean insert(Collect collect);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 查询所有收藏
     */
    public List<Collect> selectAll();

    /**
     * 查询某个用户的收藏列表
     */
    public List<Collect> selectByUserId(Integer userId);

    /**
     * 查询某个用户是否收藏某个歌曲
     */
    public boolean existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);

    /**
     * 查询某个用户是否收藏某个歌单
     */
    public boolean existSongListId(@Param("userId") Integer userId,@Param("songListId") Integer songListId);

    /**
     * 根据用户id和歌曲id删除
     */
    public boolean deleteByUserIdAndSongId(@Param("userId") Integer userId,@Param("songId") Integer songId);

    /**
     * 根据用户id和歌单id删除
     */
    public boolean deleteByUserIdAndSongListId(@Param("userId") Integer userId,@Param("songListId") Integer songListId);
}
