package com.springboot.demo01.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private static final String LOGIN_VIEW = "login";

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/login.html", "/login"})
    public ModelAndView login(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView(LOGIN_VIEW);
        String loginMsg = (String) request.getAttribute("loginMsg");

        if (!StringUtils.isEmpty(loginMsg)) {
            modelAndView.addObject("loginMsg", loginMsg);
        }

        return modelAndView;

    }

}
