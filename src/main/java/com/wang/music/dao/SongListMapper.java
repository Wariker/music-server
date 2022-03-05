package com.wang.music.dao;

import com.wang.music.domain.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongListMapper {
    /**
     * 增加
     */
    public int insertSongList(SongList songList);

    /**
     * 修改
     */
    public int updateSongList(SongList songList);

    /**
     * 删除
     */
    public int deleteSongList(Integer id);

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
     * 根据风格模糊查询
     */
    public List<SongList> likeSongListByStyle(String style);
}
