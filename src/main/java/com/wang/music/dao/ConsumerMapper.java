package com.wang.music.dao;

import com.wang.music.domain.Consumer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerMapper {
    /**
     * 增加
     */
    public int insert(Consumer consumer);

    /**
     * 修改
     */
    public int update(Consumer consumer);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 根据id查询整个对象
     */
    public Consumer selectById(Integer id);

    /**
     * 查询所有用户
     */
    public List<Consumer> selectAll();

    /**
     * 根据用户名字查询
     */
    public Consumer selectByName(String username);

    /**
     * 验证密码
     */
    public int verifyPassword(String username, String password);
}
