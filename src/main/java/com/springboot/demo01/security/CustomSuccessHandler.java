package com.springboot.demo01.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实现原始接口
 */
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String username = request.getParameter("username");
        logger.info("login success, username:" + username);

        request.getSession().setAttribute("user", username);
        /**
         * 默认是使用redirect
         */
        //request.getRequestDispatcher("/index.html").forward(request, response);

        new DefaultRedirectStrategy().sendRedirect(request,response,"/index.html"); //使用重定向方式

    }
}
