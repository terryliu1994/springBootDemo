package com.springboot.demo01.security.config;

import com.springboot.demo01.security.CustomFailureHandler;
import com.springboot.demo01.security.CustomSaveRequestSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * 定制请求授权规则
     *
     * @param http 请求
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/login.html", "/login", "/error", "/webjars/**", "/asserts/**").permitAll()
                .antMatchers("/user/toAddPage").hasRole("ADMIN") // 判断角色不需要用前缀 ROLE_
                .anyRequest().authenticated();

        // 开启自动配置的登录功能，没有权限自动跳转到登录页
        // loginPage和loginProcessingUrl保持一致但是一个是get，一个是post，这样就能防止URL提交表单
        http.formLogin().loginPage("/login").loginProcessingUrl("/login")
                //.defaultSuccessUrl("/index") // 若配置了defaultSuccessUrl则successHandler不生效
                .successHandler(new CustomSaveRequestSuccessHandler())
                .failureHandler(new CustomFailureHandler());

        // 开启注销功能
        http.logout().logoutUrl("/logout");

        // 开启记住我功能
        http.rememberMe();
    }


    /**
     * 定义认证规则
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);

        // 必须指定加密方法 否则报错:
        // There is no PasswordEncoder mapped for the id "null"
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
