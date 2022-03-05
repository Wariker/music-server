package com.wang.music.dao;

import com.wang.music.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    /**
     * 增加
     */
    public int insert(Comment comment);

    /**
     * 修改
     */
    public int update(Comment comment);

    /**
     * 删除
     */
    public int delete(Integer id);

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
