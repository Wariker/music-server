package com.wang.music.service.impl;

import com.wang.music.dao.ListSongMapper;
import com.wang.music.domain.ListSong;
import com.wang.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService{
    @Autowired
    private ListSongMapper listSongMapper;
    /**
     * 增加
     *
     * @param listSong
     */
    @Override
    public boolean insertListSong(ListSong listSong) {
        return listSongMapper.insertListSong(listSong)>0;
    }

    /**
     * 修改
     *
     * @param listSong
     */
    @Override
    public boolean updateListSong(ListSong listSong) {
        return listSongMapper.updateListSong(listSong)>0;
    }

    /**
     * 删除
     */
    @Override
    public boolean deleteListSong(Integer id) {
        return listSongMapper.deleteListSong(id)>0;
    }

    /**
     * 根据id查询整个对象
     *
     * @param id
     */
    @Override
    public ListSong selectListSongById(Integer id) {
        return listSongMapper.selectListSongById(id);
    }

    /**
     * 根据songId和songListId删除
     *
     * @param songId
     * @param songListId
     */
    @Override
    public boolean deleteBySongIdAndSongListId(Integer songId, Integer songListId) {
        return listSongMapper.deleteBySongIdAndSongListId(songId,songListId)>0;
    }

    /**
     * 查询所有的歌单歌曲
     */
    @Override
    public List<ListSong> selectAllListSong() {
        return listSongMapper.selectAllListSong();
    }

    /**
     * 根据歌单id查询歌曲
     *
     * @param songListId
     */
    @Override
    public List<ListSong> selectListSongBySongListId(Integer songListId) {
        return listSongMapper.selectListSongBySongListId(songListId);
    }
}
