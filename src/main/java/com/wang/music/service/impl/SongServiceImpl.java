package com.wang.music.service.impl;

import com.wang.music.dao.SongMapper;
import com.wang.music.domain.Song;
import com.wang.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongMapper songMapper;
    /**
     * 增加
     *
     * @param song
     */
    @Override
    public boolean insertSong(Song song) {
        return songMapper.insertSong(song)>0;
    }

    /**
     * 修改
     *
     * @param song
     */
    @Override
    public boolean updateSong(Song song) {
        return songMapper.updateSong(song)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean deleteSong(Integer id) {
        return songMapper.deleteSong(id)>0;
    }

    /**
     * 根据id查询整个对象
     *
     * @param id
     */
    @Override
    public Song selectSongById(Integer id) {
        return songMapper.selectSongById(id);
    }

    /**
     * 查询所有歌曲
     */
    @Override
    public List<Song> selectAllSong() {
        return songMapper.selectAllSong();
    }

    /**
     * 根据歌曲名字模糊查询
     *
     * @param name
     */
    @Override
    public List<Song> likeSongByName(String name) {
        return songMapper.likeSongByName("%"+name+"%");
    }

    /**
     * 根据歌手id查询
     *
     * @param singerId
     */
    @Override
    public List<Song> selectSongBySingerId(Integer singerId) {
        return songMapper.selectSongBySingerId(singerId);
    }

    /**
     * 根据歌曲名字精确查询
     *
     * @param name
     */
    @Override
    public List<Song> selectSongBySongName(String name) {
        return songMapper.selectSongBySongName(name);
    }
}
