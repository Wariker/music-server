package com.wang.music.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 前端用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {
    private Integer id;
    private String username;
    private String password;
    private Byte sex;
    private String phoneNum;
    private String email;
    private Date birth;
    private String introduction;
    private String location;
    private String avator;
    private Date createTime;
    private Date updateTime;
}
