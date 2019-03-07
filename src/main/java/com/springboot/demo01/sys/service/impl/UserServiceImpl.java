package com.springboot.demo01.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.demo01.sys.dto.User;
import com.springboot.demo01.sys.mapper.UserMapper;
import com.springboot.demo01.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public void insertUser(User user) {

        mapper.insert(user);

    }

    @Override
    public List<User> select(User user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return mapper.select(user);
    }


    @Override
    public boolean checkLogin(String userName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        List<User> list = mapper.select(user);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserByName(String userName) {
        return mapper.getUserByName(userName);
    }


}
