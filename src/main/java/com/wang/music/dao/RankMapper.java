package com.wang.music.dao;

import com.wang.music.domain.Rank;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评价
 */
@Repository
public interface RankMapper {
    /**
     * 增加
     */
    public int insertRank(Rank rank);


    /**
     * 查询总评价
     */
    public int selectScoreSum(Integer songListId);


    /**
     * 查询总评价人数
     */
    public int selectRankSum(Integer songListId);
}
