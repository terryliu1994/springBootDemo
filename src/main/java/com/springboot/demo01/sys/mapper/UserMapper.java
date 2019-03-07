package com.springboot.demo01.sys.mapper;

import com.springboot.demo01.sys.dto.User;
import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    User selectByPrimaryKey(Long userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    List<User> select(User record);

    User getUserByName(String userName);
}