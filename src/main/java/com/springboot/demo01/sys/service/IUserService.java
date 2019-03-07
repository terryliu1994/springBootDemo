package com.springboot.demo01.sys.service;

import com.springboot.demo01.sys.dto.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {

    void insertUser(User user);

    List<User> select(User user, int pageNum, int pageSize);

    boolean checkLogin(String userName, String password);

    User getUserByName(String userName);
}
