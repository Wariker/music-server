package com.wang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.wang.music.domain.Collect;
import com.wang.music.service.CollectService;
import com.wang.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * tip：可以用Collect类型，自动赋值，以后可以改
 */
@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    /**
     * 添加收藏
     */
    @PostMapping("/add")
    public Object addCollect(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId = request.getParameter("songId");
        String songListId = request.getParameter("songListId");
        //将数据保存到Collect中
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setType(Byte.valueOf(type));
        if (Byte.valueOf(type)==0){
            if(songId==null||songId.equals("")){
                jsonObject.put(Consts.CODE,0);
                jsonObject.put(Consts.MSG,"收藏歌曲不存在");
                return jsonObject;
            }
            if(collectService.existSongId(Integer.parseInt(userId),Integer.parseInt(songId))){
                jsonObject.put(Consts.CODE,2);
                jsonObject.put(Consts.MSG,"已收藏");
                return jsonObject;
            }else {
                collect.setSongId(Integer.parseInt(songId));
            }
        }else {
            if(songListId==null||songListId.equals("")){
                jsonObject.put(Consts.CODE,0);
                jsonObject.put(Consts.MSG,"收藏歌单不存在");
                return jsonObject;
            }
            if(collectService.existSongListId(Integer.parseInt(userId),Integer.parseInt(songListId))){
                jsonObject.put(Consts.CODE,2);
                jsonObject.put(Consts.MSG,"已收藏");
                return jsonObject;
            }else {
                collect.setSongListId(Integer.parseInt(songListId));
            }
        }
        boolean flag = collectService.insert(collect);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"收藏成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"收藏失败");
        }
        return jsonObject;
    }

    /**
     * 删除收藏
     */
    @GetMapping("/delete")
    public Object deleteCollect(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Integer id = Integer.parseInt(request.getParameter("id").trim());
        boolean flag = collectService.delete(id);
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
     * 根据用户id和歌曲id取消收藏
     */
    @PostMapping("/deleteByUserIdAndSongId")
    public Object deleteByUserIdAndSongId(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Integer userId = Integer.parseInt(request.getParameter("userId").trim());
        Integer songId = Integer.parseInt(request.getParameter("songId").trim());
        boolean flag = collectService.deleteByUserIdAndSongId(userId,songId);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"取消收藏成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"取消收藏失败");
        }
        return jsonObject;
    }
    /**
     * 根据歌曲和用户查询是否已经收藏
     */
    @PostMapping("/existSongId")
    public Object existSongId(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Integer userId = Integer.parseInt(request.getParameter("userId").trim());
        Integer songId = Integer.parseInt(request.getParameter("songId").trim());
        boolean flag = collectService.existSongId(userId,songId);
        if(flag){
            jsonObject.put(Consts.CODE,1);
        }else {
            jsonObject.put(Consts.CODE,0);
        }
        return jsonObject;
    }

    /**
     * 查询所有收藏
     */
    @GetMapping("/selectAll")
    public Object selectAll(){
        return collectService.selectAll();
    }

    /**
     * 查询某个用户下的所有收藏
     */
    @GetMapping("/selectByUserId")
    public Object selectByUserId(HttpServletRequest request){
        String userId = request.getParameter("userId").trim();
        return collectService.selectByUserId(Integer.parseInt(userId));
    }
}
