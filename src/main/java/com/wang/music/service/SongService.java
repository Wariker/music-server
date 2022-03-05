package com.wang.music.service;

import com.wang.music.domain.Song;

import java.util.List;

public interface SongService {
    /**
     * 增加
     */
    public boolean insertSong(Song song);

    /**
     * 修改
     */
    public boolean updateSong(Song song);

    /**
     * 删除
     */
    public boolean deleteSong(Integer id);

    /**
     * 根据id查询整个对象
     */
    public Song selectSongById(Integer id);

    /**
     * 查询所有歌曲
     */
    public List<Song> selectAllSong();

    /**
     * 根据歌曲名字模糊查询
     */
    public List<Song> likeSongByName(String name);

    /**
     * 根据歌曲名字精确查询
     */
    public List<Song> selectSongBySongName(String name);

    /**
     * 根据歌手id查询
     */
    public List<Song> selectSongBySingerId(Integer singerId);

}
