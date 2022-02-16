package com.text.demo.utils;

import com.text.demo.entity.UserEntity;
import com.text.demo.mapper.system.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Component
public class ReturnData {

    @Autowired
    private  LoginMapper loginMapper;

    @Autowired
    private  HttpServletRequest request;

    //当前工具类
    private static ReturnData returnData;

    // 1.解决静态方法中不能直接用mapper的问题 初始化工具类和mapper
    @PostConstruct
    public void init() {
        returnData = this;
        returnData.loginMapper = this.loginMapper;
        returnData.request = this.request;
    }

    public static Boolean returnData(int data){
        if(data > 0){
            return true;
        }else {
            return false;
        }
    }

    // 获取当前用户信息
    public static UserEntity getUserMsg(){
        String username =  (String)returnData.request.getSession().getAttribute("username");
        String password =  (String)returnData.request.getSession().getAttribute("password");
        UserEntity userData = returnData.loginMapper.getUserMsg(username,password);
        return userData;
    }
}
