package com.wang.music.service;

import com.wang.music.domain.SongList;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SongListService {
    /**
     * 增加
     */
    public boolean insertSongList(SongList songList);

    /**
     * 修改
     */
    public boolean updateSongList(SongList songList);

    /**
     * 删除
     */
    public boolean deleteSongList(Integer id);

    /**
     * 根据id查询整个对象
     */
    public SongList selectSongListById(Integer id);

    /**
     * 查询所有歌单
     */
    public List<SongList> selectAllSongList();

    /**
     * 根据歌单名字模糊查询
     */
    public List<SongList> likeSongListByTitle(String title);

    /**
     * 根据歌单名字精确查询
     */
    public SongList selectSongListByTitle(String title);

    /**
     * 根据风格查询
     */
    public List<SongList> likeSongListByStyle(String style);
}
