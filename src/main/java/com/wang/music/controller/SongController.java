package com.wang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.wang.music.domain.Song;
import com.wang.music.service.SongService;
import com.wang.music.utils.Consts;
import com.wang.music.utils.HandleFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;

    /**
     * 添加歌曲
     */
    @PostMapping("/add")
    public Object addSong(HttpServletRequest request, @RequestParam("file")MultipartFile mpFile){
        JSONObject jsonObject = new JSONObject();
        String singerId = request.getParameter("singerId").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();
        String pic = "/img/songPic/tubiao.jpg";
        String lyric = request.getParameter("lyric").trim();
        //上传歌曲文件
        if(mpFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"歌曲上传失败");
            return jsonObject;
        }
        //根据时间防止相同文件名覆盖文件
        String fileName = System.currentTimeMillis()+mpFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        File isFile = new File(filePath);
        //如果文件路径不存在，则新增文件
        if(!isFile.exists()){
            isFile.mkdir();
        }
        //拼出实际文件存储路径
        File desFile = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对地址
        String storeAvatorPath = "/song/"+fileName;
        try {
            mpFile.transferTo(desFile);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singerId));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeAvatorPath);
            boolean flag = songService.insertSong(song);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传歌曲成功");
                jsonObject.put("avator",storeAvatorPath);
                return jsonObject;
            }else {
                jsonObject.put(Consts.CODE,0);
                jsonObject.put(Consts.MSG,"上传歌曲失败");
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传歌曲失败"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }

    /**
     * 根据歌手id查询歌曲
     */
    @GetMapping("/singer/detail")
    public Object selectSongBySingerId(HttpServletRequest request){
        String singerId = request.getParameter("singerId");
        return songService.selectSongBySingerId(Integer.parseInt(singerId));
    }

    /**
     * 修改歌曲
     */
    @PostMapping("/update")
    public Object updateSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();
        String lyric = request.getParameter("lyric").trim();
        //将数据保存到Song中
        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setLyric(lyric);
        boolean flag = songService.updateSong(song);
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
     * 删除歌曲
     */
    @GetMapping("/delete")
    public Object deleteSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Integer id = Integer.parseInt(request.getParameter("id").trim());
        String url = songService.selectSongById(id).getUrl();
        if (HandleFile.deleteSongFile(url)){
            boolean flag = songService.deleteSong(id);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"删除成功");
            }else {
                jsonObject.put(Consts.CODE,0);
                jsonObject.put(Consts.MSG,"删除失败");
            }
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"删除失败");
        }
        return jsonObject;
    }

    /**
     * 更新歌曲图片
     */
    @PostMapping("/updateSongPic")
    public Object updateSongPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
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
                +System.getProperty("file.separator")+"songPic";
        File isFile = new File(filePath);
        //如果文件路径不存在，则新增文件
        if(!isFile.exists()){
            isFile.mkdir();
        }
        //拼出实际文件存储路径
        File desFile = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对地址
        String storeAvatorPath = "/img/songPic/"+fileName;
        try {
            avatorFile.transferTo(desFile);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeAvatorPath);
            boolean flag = songService.updateSong(song);
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

    /**
     * 更新歌曲
     */
    @PostMapping("/updateSongUrl")
    public Object updateSong(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改歌曲失败");
            return jsonObject;
        }
        //根据时间防止相同文件名覆盖文件
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        File isFile = new File(filePath);
        //如果文件路径不存在，则新增文件
        if(!isFile.exists()){
            isFile.mkdir();
        }
        String url = songService.selectSongById(id).getUrl();
        if (HandleFile.deleteSongFile(url)){
            //拼出实际文件存储路径
            File desFile = new File(filePath+System.getProperty("file.separator")+fileName);
            //存储到数据库里的相对地址
            String storeAvatorPath = "/song/"+fileName;
            try {
                avatorFile.transferTo(desFile);
                Song song = new Song();
                song.setId(id);
                song.setUrl(storeAvatorPath);
                boolean flag = songService.updateSong(song);
                if(flag){
                    jsonObject.put(Consts.CODE,1);
                    jsonObject.put(Consts.MSG,"修改歌曲成功");
                    jsonObject.put("avator",storeAvatorPath);
                    return jsonObject;
                }else {
                    jsonObject.put(Consts.CODE,0);
                    jsonObject.put(Consts.MSG,"修改歌曲失败");
                    return jsonObject;
                }
            } catch (IOException e) {

            }finally {
                return jsonObject;
            }
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改歌曲失败");
            return jsonObject;
        }
    }

    /**
     * 根据歌曲id查询
     */
    @GetMapping("/detail")
    public Object selectSongById(HttpServletRequest request){
        String id = request.getParameter("id");
        return songService.selectSongById(Integer.parseInt(id));
    }

    /**
     * 根据歌曲名字精确查询
     */
    @GetMapping("/selectSongByName")
    public Object selectSongByName(HttpServletRequest request){
        String name = request.getParameter("name");
        return songService.selectSongBySongName(name);
    }

    /**
     * 根据歌曲名字模糊查询
     */
    @GetMapping("/likeSongByName")
    public Object likeSongByName(HttpServletRequest request){
        String name = request.getParameter("name");
        return songService.likeSongByName(name);
    }

    /**
     * 查询所有歌曲
     */
    @GetMapping("/selectAll")
    public Object selectAllSong(){
        return songService.selectAllSong();
    }
}
