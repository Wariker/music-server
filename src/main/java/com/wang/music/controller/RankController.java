package com.wang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.wang.music.domain.Rank;
import com.wang.music.service.RankService;
import com.wang.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RankController {

    @Autowired
    private RankService rankService;

    /**
     * 新增评分
     */
    @PostMapping("/rank/add")
    public Object add(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String songListId = request.getParameter("songListId");
        String consumerId = request.getParameter("consumerId");
        String score = request.getParameter("score");
        Rank rank = new Rank();
        rank.setSongListId(Integer.parseInt(songListId));
        rank.setConsumerId(Integer.parseInt(consumerId));
        rank.setScore(Integer.parseInt(score));
        boolean flag = rankService.insertRank(rank);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"评价成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"评价失败");
        }
        return jsonObject;
    }

    /**
     * 计算平均分
     */
    @GetMapping("/rank/average")
    public Object average(HttpServletRequest request) {
        String songListId = request.getParameter("songListId");
        return rankService.rangOfSongListId(Integer.parseInt(songListId));
    }
}
