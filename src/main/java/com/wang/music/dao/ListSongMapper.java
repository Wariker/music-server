package com.wang.music.dao;

import com.wang.music.domain.ListSong;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListSongMapper {
    /**
     * 增加
     */
    public int insertListSong(ListSong listSong);

    /**
     * 修改
     */
    public int updateListSong(ListSong listSong);

    /**
     * 删除
     */
    public int deleteListSong(Integer id);

    /**
     * 根据songId和songListId删除
     */
    public int deleteBySongIdAndSongListId(Integer songId,Integer songListId);

    /**
     * 根据id查询整个对象
     */
    public ListSong selectListSongById(Integer id);

    /**
     * 查询所有的歌单歌曲
     */
    public List<ListSong> selectAllListSong();

    /**
     * 根据歌单id查询歌曲
     */
    public List<ListSong> selectListSongBySongListId(Integer songListId);
}
