package com.text.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        //判断session中是否存在用户
//        String user =  (String)request.getSession().getAttribute("username");
//        if(user != null && !user.equals("")){
//            //request.getRequestDispatcher("/index").forward(request,response);
//            return true;
//        }else {
//            //请求转发到登录界面
//            request.setAttribute("msg","用户未登入");
//            request.getRequestDispatcher("/login").forward(request,response);
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
