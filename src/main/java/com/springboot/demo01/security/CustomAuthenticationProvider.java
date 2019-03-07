/**
 * 文件名：CustomAuthenticationProvider.java
 * 版权：
 * 描述：
 */
package com.springboot.demo01.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 可以使用自定义的DaoAuthenticationProvider
 */
@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    @Qualifier("customUserDetailsService")
    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }




}
