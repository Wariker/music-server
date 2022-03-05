package com.wang.music.dao;

import com.wang.music.domain.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper {
    /**
     * 增加
     */
    public int insert(Collect collect);

    /**
     * 根据id删除
     */
    public int delete(Integer id);

    /**
     * 根据用户id和歌曲id删除
     */
    public int deleteByUserIdAndSongId(@Param("userId") Integer userId,@Param("songId") Integer songId);

    /**
     * 根据用户id和歌单id删除
     */
    public int deleteByUserIdAndSongListId(@Param("userId") Integer userId,@Param("songListId") Integer songListId);

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
    public int existSongId(@Param("userId") Integer userId,@Param("songId") Integer songId);

    /**
     * 查询某个用户是否收藏某个歌单
     */
    public int existSongListId(@Param("userId") Integer userId,@Param("songListId") Integer songListId);
}