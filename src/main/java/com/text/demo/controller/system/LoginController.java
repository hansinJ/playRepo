package com.text.demo.controller.system;

import com.text.demo.entity.UserEntity;
import com.text.demo.service.system.LoginService;
import com.text.demo.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    LoginService loginService;

    @RequestMapping("login")
    @ResponseBody
    public JsonResult login(String username,String password) {
        //登入
        JsonResult res = new JsonResult();
        try {
            Boolean flag = loginService.login(username,password);

            if(flag == true){
                res.setCode(200);
                res.setMsg("登入成功");
            }else{
                res.setCode(400);
                res.setMsg("用户名或密码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("back")
    @ResponseBody
    public JsonResult back() {
        // 退出登入
        JsonResult res = new JsonResult();
        try {
            res.setCode(200);
        }catch (Exception e){
            res.setCode(400);
            e.printStackTrace();
        }
        return res;
    }

}
