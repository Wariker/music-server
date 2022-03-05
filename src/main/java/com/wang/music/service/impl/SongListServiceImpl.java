package com.wang.music.service.impl;

import com.wang.music.dao.SongListMapper;
import com.wang.music.domain.SongList;
import com.wang.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;
    /**
     * 增加
     *
     * @param songList
     */
    @Override
    public boolean insertSongList(SongList songList) {
        return songListMapper.insertSongList(songList)>0;
    }

    /**
     * 修改
     *
     * @param songList
     */
    @Override
    public boolean updateSongList(SongList songList) {
        return songListMapper.updateSongList(songList)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean deleteSongList(Integer id) {
        return songListMapper.deleteSongList(id)>0;
    }

    /**
     * 根据id查询整个对象
     *
     * @param id
     */
    @Override
    public SongList selectSongListById(Integer id) {
        return songListMapper.selectSongListById(id);
    }

    /**
     * 查询所有歌单
     */
    @Override
    public List<SongList> selectAllSongList() {
        return songListMapper.selectAllSongList();
    }

    /**
     * 根据歌单名字模糊查询
     *
     * @param title
     */
    @Override
    public List<SongList> likeSongListByTitle(String title) {
        return songListMapper.likeSongListByTitle(title);
    }

    /**
     * 根据歌单名字精确查询
     *
     * @param title
     */
    @Override
    public SongList selectSongListByTitle(String title) {
        return songListMapper.selectSongListByTitle(title);
    }

    /**
     * 根据风格查询
     *
     * @param style
     */
    @Override
    public List<SongList> likeSongListByStyle(String style) {
        return songListMapper.likeSongListByStyle(style);
    }
}
