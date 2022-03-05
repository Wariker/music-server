package com.wang.music.service;

import com.wang.music.domain.Consumer;

import java.util.List;

public interface ConsumerService {
    /**
     * 增加
     */
    public boolean insert(Consumer consumer);

    /**
     * 修改
     */
    public boolean update(Consumer consumer);

    /**
     * 删除
     */
    public boolean delete(Integer id);

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
    public boolean verifyPassword(String username, String password);
}
