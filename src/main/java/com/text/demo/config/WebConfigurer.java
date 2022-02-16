package com.text.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射静态资源访问路径，比如html，js，css，等等
        //告诉spring 静态文件访问地址
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有请求，排除两个请求"/index","/login"，不需要拦截登录请求
        //注册拦截器
        //registry.addInterceptor(loginInterceptor)
       //         .addPathPatterns("/**")
        //        .excludePathPatterns("/login","/","/toIndex","","/toPhoneNub","/toRegister","/addCountry","/register");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //默认路径登录页面
        //registry.addViewController("/").setViewName("login");
        //设置过滤优先级最高
       // registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
