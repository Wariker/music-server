package com.wang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.wang.music.domain.Singer;
import com.wang.music.service.SingerService;
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
 * tip：可以用Singer类型，自动赋值，以后可以改
 */
//-TODO 删除歌手后应当将他的歌曲也一同删除
@RestController
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;

    /**
     * 添加歌手
     */
    @PostMapping("/add")
    public Object addSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String pic = request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        //把生日转换城Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //将数据保存到Singer中
        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(Byte.valueOf(sex));
        singer.setPic(pic);
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean flag = singerService.insert(singer);
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
     * 修改歌手
     */
    @PostMapping("/update")
    public Object updateSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        //String pic = request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        //把生日转换城Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //将数据保存到Singer中
        Singer singer = new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(Byte.valueOf(sex));
        //singer.setPic(pic);
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean flag = singerService.update(singer);
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
    public Object deleteSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Integer id = Integer.parseInt(request.getParameter("id").trim());
        boolean flag = singerService.delete(id);
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
        return singerService.selectById(id);
    }

    /**
     * 查询所有歌手
     */
    @GetMapping("/selectAll")
    public Object selectAll(){
        return singerService.selectAll();
    }

    /**
     * 根据歌手名字模糊查询
     */
    @GetMapping("/selectByName")
    public Object selectByName(HttpServletRequest request){
        String name = request.getParameter("name").trim();
        return singerService.selectByName("%"+name+"%");
    }

    /**
     * 根据性别查询
     */
    @GetMapping("/selectBySex")
    public Object selectBySex(HttpServletRequest request){
        Integer sex = Integer.parseInt(request.getParameter("sex").trim());
        return singerService.selectBySex(sex);
    }

    /**
     * 更新歌手图片
     */
    @PostMapping("/updateSingerPic")
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
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
                +System.getProperty("file.separator")+"singerPic";
        File isFile = new File(filePath);
        //如果文件路径不存在，则新增文件
        if(!isFile.exists()){
            isFile.mkdir();
        }
        //拼出实际文件存储路径
        File desFile = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对地址
        String storeAvatorPath = "/img/singerPic/"+fileName;
        try {
            avatorFile.transferTo(desFile);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean flag = singerService.update(singer);
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
