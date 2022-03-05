package com.wang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.wang.music.domain.SongList;
import com.wang.music.service.SongListService;
import com.wang.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 歌单
 */
@RestController
@RequestMapping("/songList")
public class SongListController {
    @Autowired
    private SongListService songListService;

    /**
     * 添加歌单
     */
    @PostMapping("/add")
    public Object addSongList(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String title = request.getParameter("title").trim();
        String introduction = request.getParameter("introduction").trim();
        String pic = request.getParameter("pic").trim();
        String style = request.getParameter("style").trim();
        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setPic(pic);
        songList.setStyle(style);
        boolean flag = songListService.insertSongList(songList);
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
     * 修改歌单
     */
    @PostMapping("/update")
    public Object updateSongList(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String title = request.getParameter("title").trim();
        String style = request.getParameter("style").trim();
        String introduction = request.getParameter("introduction").trim();
        //将数据保存到SongList中
        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setStyle(style);
        songList.setIntroduction(introduction);
        boolean flag = songListService.updateSongList(songList);
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
     * 删除歌手
     */
    @GetMapping("/delete")
    public Object deleteSongList(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Integer id = Integer.parseInt(request.getParameter("id").trim());
        boolean flag = songListService.deleteSongList(id);
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
        return songListService.selectSongListById(id);
    }

    /**
     * 查询所有歌单
     */
    @GetMapping("/selectAll")
    public Object selectAll(){
        return songListService.selectAllSongList();
    }

    /**
     * 根据歌单标题精确查询song_list
     */
    @GetMapping("/selectByName")
    public Object selectByName(HttpServletRequest request){
        String title = request.getParameter("title").trim();
        return songListService.selectSongListByTitle(title);
    }

    /**
     * 根据歌单标题模糊查询
     */
    @GetMapping("/likeSongListByTitle")
    public Object likeSongListByTitle(HttpServletRequest request){
        String title = request.getParameter("title").trim();
        return songListService.likeSongListByTitle("%"+title+"%");
    }

    /**
     * 根据风格模糊查询
     */
    @GetMapping("/likeSongListByStyle")
    public Object likeSongListByStyle(HttpServletRequest request){
        String style = request.getParameter("style").trim();
        return songListService.likeSongListByStyle("%"+style+"%");
    }

    /**
     * 更新歌单图片
     */
    @PostMapping("/updateSongListPic")
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改图片失败");
            return jsonObject;
        }
        //根据时间防止相同文件名覆盖文件
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songListPic";
        File isFile = new File(filePath);
        //如果文件路径不存在，则新增文件
        if(!isFile.exists()){
            isFile.mkdir();
        }
        //拼出实际文件存储路径
        File desFile = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对地址
        String storeAvatorPath = "/img/songListPic/"+fileName;
        try {
            avatorFile.transferTo(desFile);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            boolean flag = songListService.updateSongList(songList);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"修改图片成功");
                jsonObject.put("pic",storeAvatorPath);
                return jsonObject;
            }else {
                jsonObject.put(Consts.CODE,0);
                jsonObject.put(Consts.MSG,"修改图片失败");
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改图片失败"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }
}
