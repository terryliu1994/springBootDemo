package com.springboot.demo01.security.impl;

import com.springboot.demo01.security.dto.PermissionUser;
import com.springboot.demo01.sys.dto.User;
import com.springboot.demo01.sys.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 客户化UserDetailsService，定制用户认证实现
 *
 * @author terry
 * @version 1.0
 * @date 2018/12/3 17:09
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        logger.info("login user:" + s);

        User user = userService.getUserByName(s);
        if (user == null) {
            throw new UsernameNotFoundException("user is not found");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // 设置角色需要用前缀 ROLE_
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if ("ADMIN".equalsIgnoreCase(s)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

        return new PermissionUser(user.getUserName(), encodedPassword, authorities);
    }
}
