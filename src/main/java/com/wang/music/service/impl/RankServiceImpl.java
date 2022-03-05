package com.wang.music.service.impl;

import com.wang.music.dao.RankMapper;
import com.wang.music.domain.Rank;
import com.wang.music.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankMapper rankMapper;
    /**
     * 增加
     *
     * @param rank
     */
    @Override
    public boolean insertRank(Rank rank) {
        return rankMapper.insertRank(rank)>0;
    }

    /**
     * 查询总评价
     *
     * @param songListId
     */
    @Override
    public int selectScoreSum(Integer songListId) {
        return rankMapper.selectScoreSum(songListId);
    }

    /**
     * 查询总评价人数
     *
     * @param songListId
     */
    @Override
    public int selectRankSum(Integer songListId) {
        return rankMapper.selectRankSum(songListId);
    }

    /**
     * 计算平均分
     *
     * @param songListId
     */
    @Override
    public int rangOfSongListId(Integer songListId) {
        int rankNum = rankMapper.selectRankSum(songListId);
        if(rankNum == 0){
            return 5;
        }
        return rankMapper.selectScoreSum(songListId)/rankNum;
    }
}
