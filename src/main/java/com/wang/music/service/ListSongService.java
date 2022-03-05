package com.wang.music.service;

import com.wang.music.domain.ListSong;

import java.util.List;

public interface ListSongService {
    /**
     * 增加
     */
    public boolean insertListSong(ListSong listSong);

    /**
     * 修改
     */
    public boolean updateListSong(ListSong listSong);

    /**
     * 删除
     */
    public boolean deleteListSong(Integer id);

    /**
     * 根据songId和songListId删除
     */
    public boolean deleteBySongIdAndSongListId(Integer songId,Integer songListId);

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
