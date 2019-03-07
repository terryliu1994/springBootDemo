package com.springboot.demo01.config;

import com.springboot.demo01.sys.interceptor.LoginHandlerInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 1、自定义SpringMvc配置，注意继承抽象类 WebMvcConfigurerAdapter 已经过时
 * spring 5.0后要使用Java8，而在Java8中接口是可以有default方法，所以不需要再使用抽象类，直接实现接口
 * <p>
 * 3、可以继承 WebMvcConfigurationSupport 完全接管SpringBoot的自动配置，这是因为在 springboot的web 自动配置类
 * WebMvcAutoConfiguration 上有条件注解 @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加请求视图映射
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 需要排除
        // SpringBoot 1.5.x中，resources/static目录下的静态资源可以直接访问
        // SpringBoot 2.x时，访问静态资源就会被HandlerInterceptor拦截
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/login.html", "/login", "/user/login", "/error", "/webjars/**", "/asserts/**");
    }

    /**
     * 自定义LocaleResolver加入容器，并加入SpringMVC配置
     *
     * @return LocaleResolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
