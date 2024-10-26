package edu.northeastern.cs5200.ect.service.impl;

import edu.northeastern.cs5200.ect.mapper.UserMapper;
import edu.northeastern.cs5200.ect.pojo.User;
import edu.northeastern.cs5200.ect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(String name) {
        System.out.println(userMapper.get(name));
        return userMapper.get(name);
    }
}
