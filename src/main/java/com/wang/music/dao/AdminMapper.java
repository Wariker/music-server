package com.wang.music.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface AdminMapper {
    //验证密码是否正确
    public int verifyPassword(String username,String password);
}
