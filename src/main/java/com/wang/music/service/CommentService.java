package com.wang.music.service;

import com.wang.music.domain.Comment;

import java.util.List;

public interface CommentService {
    /**
     * 增加
     */
    public boolean insert(Comment comment);

    /**
     * 修改
     */
    public boolean update(Comment comment);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据id查询整个对象
     */
    public Comment selectById(Integer id);

    /**
     * 查询所有评论
     */
    public List<Comment> selectAll();

    /**
     * 查询某个歌曲下的所有评论
     */
    public List<Comment> selectBySongId(Integer songId);

    /**
     * 查询某个歌单下的所有评论
     */
    public List<Comment> selectBySongListId(Integer songListId);
}
