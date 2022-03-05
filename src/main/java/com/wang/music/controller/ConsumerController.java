package com.wang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.wang.music.domain.Consumer;
import com.wang.music.service.ConsumerService;
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

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    /**
     * 添加用户
     */
    @PostMapping("/add")
    public Object addConsumer(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String phoneNum = request.getParameter("phoneNum").trim();
        String email = request.getParameter("email").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        String avator = request.getParameter("avator").trim();

        if(username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空！");
            return jsonObject;
        }

        if(consumerService.selectByName(username)!=null){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名已经存在！");
            return jsonObject;
        }

        if(password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空！");
            return jsonObject;
        }
        //把生日转换城Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //将数据保存到Consumer中
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(Byte.valueOf(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setLocation(location);
        consumer.setIntroduction(introduction);
        consumer.setAvator(avator);
        boolean flag = consumerService.insert(consumer);
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
     * 修改用户
     */
    @PostMapping("/update")
    public Object updateConsumer(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String phoneNum = request.getParameter("phoneNum").trim();
        String email = request.getParameter("email").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        //String avator = request.getParameter("avator").trim();
        //把生日转换城Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //将数据保存到Consumer中
        Consumer consumer = new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(Byte.valueOf(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setLocation(location);
        consumer.setIntroduction(introduction);
        //consumer.setAvator(avator);
        boolean flag = consumerService.update(consumer);
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
     * 删除用户
     */
    @GetMapping("/delete")
    public Object deleteConsumer(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Integer id = Integer.parseInt(request.getParameter("id").trim());
        boolean flag = consumerService.delete(id);
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
        return consumerService.selectById(id);
    }

    /**
     * 查询所有用户
     */
    @GetMapping("/selectAll")
    public Object selectAll(){
        return consumerService.selectAll();
    }

    /**
     * 根据用户名字精确查询
     */
    @GetMapping("/selectByName")
    public Object selectByName(HttpServletRequest request){
        String username = request.getParameter("username").trim();
        return consumerService.selectByName(username);
    }

    /**
     * 更新用户图片
     */
    @PostMapping("/updateConsumerPic")
    public Object updateConsumerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改图片失败");
            return jsonObject;
        }
        //根据时间防止相同文件名覆盖文件
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"avatorImages";
        File isFile = new File(filePath);
        //如果文件路径不存在，则新增文件
        if(!isFile.exists()){
            isFile.mkdir();
        }
        //拼出实际文件存储路径
        File desFile = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对地址
        String storeAvatorPath = "/avatorImages/"+fileName;
        try {
            avatorFile.transferTo(desFile);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatorPath);
            boolean flag = consumerService.update(consumer);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"修改图片成功");
                jsonObject.put("avator",storeAvatorPath);
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
     * 用户登陆
     */
    @PostMapping("/login")
    public Object login(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        if(username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空！");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空！");
            return jsonObject;
        }
        boolean flag = consumerService.verifyPassword(username,password);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登陆成功");
            jsonObject.put("userMsg",consumerService.selectByName(username));
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名或密码错误");
        }
        return jsonObject;
    }
}
