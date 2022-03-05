package com.wang.music.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 歌单歌曲
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListSong implements Serializable {
    private Integer id;
    private Integer songId;
    private Integer songListId;
}
