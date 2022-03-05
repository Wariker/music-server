package com.wang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.wang.music.domain.Comment;
import com.wang.music.service.CommentService;
import com.wang.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * tip：可以用Comment类型，自动赋值，以后可以改
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     */
    @PostMapping("/add")
    public Object addComment(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId = request.getParameter("songId");
        String songListId = request.getParameter("songListId");
        String content = request.getParameter("content").trim();
        //将数据保存到Comment中
        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(Byte.valueOf(type));
        if (Byte.valueOf(type)==0){
            comment.setSongId(Integer.parseInt(songId));
        }else {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        boolean flag = commentService.insert(comment);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
        }
        return jsonObject;
    }

    /**
     * 修改评论
     */
    @PostMapping("/update")
    public Object updateComment(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String userId = request.getParameter("userId").trim();
        String type = request.getParameter("type").trim();
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId").trim();
        String content = request.getParameter("content").trim();
        //将数据保存到Comment中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(Byte.valueOf(type));
        if(songId!=null&&songId.equals("")){
            songId = null;
        }else {
            comment.setSongId(Integer.parseInt(songId));
        }
        if(songListId!=null&&songListId.equals("")){
            songListId = null;
        }else {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        boolean flag = commentService.update(comment);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
        }
        return jsonObject;
    }

    /**
     * 删除评论
     */
    @GetMapping("/delete")
    public Object deleteComment(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Integer id = Integer.parseInt(request.getParameter("id").trim());
        boolean flag = commentService.delete(id);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"删除成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"删除失败");
        }
        return jsonObject;
    }

    /**
     * 根据id查询整个对象
     */
    @GetMapping("/selectById")
    public Object  selectById(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id").trim());
        return commentService.selectById(id);
    }

    /**
     * 查询所有评论
     */
    @GetMapping("/selectAll")
    public Object selectAll(){
        return commentService.selectAll();
    }

    /**
     * 查询某个歌曲下的所有评论
     */
    @GetMapping("/selectBySongId")
    public Object selectBySongId(HttpServletRequest request){
        String songId = request.getParameter("songId").trim();
        return commentService.selectBySongId(Integer.parseInt(songId));
    }

    /**
     * 查询某个歌单下的所有评论
     */
    @GetMapping("/selectBySongListId")
    public Object selectBySongListId(HttpServletRequest request){
        Integer songListId = Integer.parseInt(request.getParameter("songListId").trim());
        return commentService.selectBySongListId(songListId);
    }

    /**
     * 给某个评论点赞
     */
    @PostMapping("/like")
    public Object like(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String up = request.getParameter("up").trim();
        //将数据保存到Comment中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));
        boolean flag = commentService.update(comment);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"点赞成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"点赞失败");
        }
        return jsonObject;
    }
}
