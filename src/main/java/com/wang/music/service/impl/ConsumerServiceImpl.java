package com.wang.music.service.impl;

import com.wang.music.dao.ConsumerMapper;
import com.wang.music.domain.Consumer;
import com.wang.music.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;
    /**
     * 增加
     *
     * @param consumer
     */
    @Override
    public boolean insert(Consumer consumer) {
        return consumerMapper.insert(consumer)>0;
    }

    /**
     * 修改
     *
     * @param consumer
     */
    @Override
    public boolean update(Consumer consumer) {
        return consumerMapper.update(consumer)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return consumerMapper.delete(id)>0;
    }

    /**
     * 根据id查询整个对象
     *
     * @param id
     */
    @Override
    public Consumer selectById(Integer id) {
        return consumerMapper.selectById(id);
    }

    /**
     * 查询所有用户
     */
    @Override
    public List<Consumer> selectAll() {
        return consumerMapper.selectAll();
    }

    /**
     * 根据用户名字查询
     *
     * @param username
     */
    @Override
    public Consumer selectByName(String username) {
        return consumerMapper.selectByName(username);
    }

    /**
     * 验证密码
     *
     * @param username
     * @param password
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        return consumerMapper.verifyPassword(username, password)>0;
    }
}
