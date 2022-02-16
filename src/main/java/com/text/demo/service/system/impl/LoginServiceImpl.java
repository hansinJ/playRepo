package com.text.demo.service.system.impl;

import com.text.demo.entity.UserEntity;
import com.text.demo.mapper.system.LoginMapper;
import com.text.demo.service.system.LoginService;
import com.text.demo.utils.JsonResult;
import com.text.demo.utils.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    HttpServletRequest request;

//    @Value("${messageTime}")
//    int messageTime;

    @Override
    public Boolean login(String username,String password) {
        UserEntity userMsg = loginMapper.getUserMsg(username,password);
        if(null != userMsg){
            request.getSession().setAttribute("username",userMsg.getUsername());
            request.getSession().setAttribute("password",userMsg.getPassword());
            return true;
        }else {
            return false;
        }
    }


}
