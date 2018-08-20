package com.zyz.demo.sharding.service;

import com.zyz.demo.sharding.mapper.UserMapper;
import com.zyz.demo.sharding.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User selectUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    public boolean saveUser(User user) {
        return userMapper.addUser(user);
    }
}
