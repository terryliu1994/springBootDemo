package com.springboot.demo01.sys.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.demo01.sys.dto.User;
import com.springboot.demo01.sys.service.IUserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/user/query")
    @ResponseBody
    public List<User> query(HttpServletRequest request, String userName,
                            @RequestParam(defaultValue = "1") int pageNum,
                            @RequestParam(defaultValue = "10") int pageSize) {
        User user = new User();
        user.setUserName(userName);
        return userService.select(user, pageNum, pageSize);
    }


    // SpringSecurity 配置 loginProcessUrl 是此Url后则不会进入此controller
    // @RequestMapping(value = "/user/login",method = RequestMethod.POST) 可以用PostMapping代替
    /*@PostMapping(value = "/user/login")
    public ModelAndView login(HttpServletRequest request, String username, String password) {

        logger.debug("username:" + username);
        logger.debug("password:" + password);

        ModelAndView modelAndView = new ModelAndView();
        if (userService.checkLogin(username, password)) {
            request.getSession().setAttribute("user", username);
            modelAndView.setViewName("redirect:index");
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("msg", "用户名密码错误");
        }
        return modelAndView;

    }*/

    /*@PostMapping(value = "/user/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "login.html";
    }*/


    @RequestMapping(value = "/sys/users")
    public ModelAndView users(User user,
                              @RequestParam(defaultValue = "1") int pageNum,
                              @RequestParam(defaultValue = "10") int pageSize) {
        ModelAndView modelAndView = new ModelAndView("sys/user");
        List<User> list = userService.select(user, pageNum, pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        modelAndView.addObject("userInfo", pageInfo);
        return modelAndView;
    }

    @RequestMapping(value = "/user/toAddPage")
    public String toAddPage() {
        return "sys/user_add";
    }

    @PostMapping(value = "/user/add")
    public String addUser(User user) {
        userService.insertUser(user);
        return "redirect:/sys/users";
    }
}
