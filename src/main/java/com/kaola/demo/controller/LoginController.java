package com.kaola.demo.controller;

import com.kaola.demo.enums.CodeMsg;
import com.kaola.demo.model.ResultMap;
import com.kaola.demo.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @PostMapping ("/login")
    @ResponseBody
    public ResultMap login(String account, String password, HttpSession session){

        ResultMap resultMap = loginService.login(account, password);
        if(resultMap.getCode() == 200){
            session.setAttribute("user",resultMap.getData());
        }
        return resultMap;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){

       session.invalidate();

       // return ResultMap.genResultMap(CodeMsg.SUCCESS);
        return "login";
    }

    @GetMapping("api/login")
    public ModelAndView loginIndex(){
        return new ModelAndView("login");
    }



    @GetMapping("api/isLogin")
    @ResponseBody
    public ResultMap isLogin(HttpSession session){
        if (session.getAttribute("user") != null) {
           return ResultMap.genResultMap(CodeMsg.SUCCESS);
        } else {
            return ResultMap.genResultMap(CodeMsg.ERROR);
        }
    }
}
