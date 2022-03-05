package com.wang.music.service;

import com.wang.music.domain.Rank;

public interface RankService {
    /**
     * 增加
     */
    public boolean insertRank(Rank rank);


    /**
     * 查询总评价
     */
    public int selectScoreSum(Integer songListId);


    /**
     * 查询总评价人数
     */
    public int selectRankSum(Integer songListId);

    /**
     * 计算平均分
     */
    public int rangOfSongListId(Integer songListId);
}
