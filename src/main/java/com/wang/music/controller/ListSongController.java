package com.wang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.wang.music.domain.ListSong;
import com.wang.music.service.ListSongService;
import com.wang.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/listSong")
public class ListSongController {
    @Autowired
    private ListSongService listSongService;

    /**
     * 添加
     */
    @PostMapping("/add")
    public Object addListSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId").trim();
        ListSong listSong = new ListSong();
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        boolean flag = listSongService.insertListSong(listSong);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
            return jsonObject;
        }
    }

    /**
     * 根据歌单id查询
     */
    @GetMapping("/detail")
    public Object selectListSongBySongListId(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        return listSongService.selectListSongBySongListId(Integer.parseInt(songListId));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public Object updateSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId").trim();
        ListSong listSong = new ListSong();
        listSong.setId(Integer.parseInt(id));
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        boolean flag = listSongService.updateListSong(listSong);
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
     * 删除歌单里的歌曲
     */
    @GetMapping("/delete")
    public Object deleteListSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Integer songId = Integer.parseInt(request.getParameter("songId").trim());
        Integer songListId = Integer.parseInt(request.getParameter("songListId").trim());
        boolean flag = listSongService.deleteBySongIdAndSongListId(songId,songListId);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"删除成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"删除失败");
        }
        return jsonObject;
    }
}
