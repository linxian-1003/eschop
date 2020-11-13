package com.neu.auth_service.controller;


import com.neu.auth_service.domain.Auth;
import com.neu.auth_service.service.AuthService;
import com.neu.auth_service.utils.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("auths")
@Controller
public class AuthController {

    @Autowired
    AuthService authService;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @PostMapping("login")
    @ResponseBody
    public JsonModel auth(@RequestBody Auth auth, HttpServletResponse response){

        auth.setLoginName(auth.getLoginName());
        auth.setPassword(auth.getPassword());
        JsonModel jsonModel = new JsonModel();
        String token = authService.auth(auth);

        if(token == null){
            jsonModel.setMsg("账号或密码错误");
            jsonModel.setSuccess(false);
        }else{
            Cookie cookie = new Cookie("token",token);
            cookie.setMaxAge(1800);
            response.addCookie(cookie);
            jsonModel.setMsg("登录成功");
            jsonModel.setSuccess(true);
        }
        return jsonModel;

    }

    //CookieValue获取指定名的cookie值
    @GetMapping("info")
    @ResponseBody
    public JsonModel parseToken(@CookieValue("token") String token){
   /*     if(token==null || "".equals(token)){
            //网关处理好的话,这里一定有值
        }*/
        String oriInfo = authService.info(token);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        jsonModel.setMsg(oriInfo);
        return jsonModel;
/*        if(oriInfo==null){
            //原始信息解析失败
        }*/
    }




}
