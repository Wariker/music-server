package com.wang.music.service.impl;

import com.wang.music.dao.CommentMapper;
import com.wang.music.domain.Comment;
import com.wang.music.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    /**
     * 增加
     *
     * @param comment
     */
    @Override
    public boolean insert(Comment comment) {
        return commentMapper.insert(comment)>0;
    }

    /**
     * 修改
     *
     * @param comment
     */
    @Override
    public boolean update(Comment comment) {
        return commentMapper.update(comment)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return commentMapper.delete(id)>0;
    }

    /**
     * 根据id查询整个对象
     *
     * @param id
     */
    @Override
    public Comment selectById(Integer id) {
        return commentMapper.selectById(id);
    }

    /**
     * 查询所有评论
     */
    @Override
    public List<Comment> selectAll() {
        return commentMapper.selectAll();
    }

    /**
     * 查询某个歌曲下的所有评论
     *
     * @param songId
     */
    @Override
    public List<Comment> selectBySongId(Integer songId) {
        return commentMapper.selectBySongId(songId);
    }

    /**
     * 查询某个歌单下的所有评论
     *
     * @param songListId
     */
    @Override
    public List<Comment> selectBySongListId(Integer songListId) {
        return commentMapper.selectBySongListId(songListId);
    }
}
