package com.wang.music.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 评价
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rank implements Serializable {
    private Integer id;
    private Integer songListId;
    private Integer consumerId;
    private Integer score;
}
