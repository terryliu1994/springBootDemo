/**
 * 文件名：CustomFailureHandler.java
 * 版权：
 * 描述：
 */
package com.springboot.demo01.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String msg = exception.getMessage();
        // Bad credentials - 用户名密码错误
        if(exception instanceof BadCredentialsException){
            msg = "用户名密码错误";
        }
        // 设置默认跳转地址
        setDefaultFailureUrl("/login.html");
        // 使用转发模式
        setUseForward(true);
        request.setAttribute("loginMsg", msg);
        //request.getSession().setAttribute("loginMsg", msg);
        super.onAuthenticationFailure(request, response, exception);

    }
}
