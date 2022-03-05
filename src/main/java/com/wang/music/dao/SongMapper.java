package com.wang.music.dao;

import com.wang.music.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongMapper {
    /**
     * 增加
     */
    public int insertSong(Song song);

    /**
     * 修改
     */
    public int updateSong(Song song);

    /**
     * 删除
     */
    public int deleteSong(Integer id);

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
