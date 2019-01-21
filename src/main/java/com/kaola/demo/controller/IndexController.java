package com.kaola.demo.controller;

import com.kaola.demo.meta.User;
import com.kaola.demo.model.ResultMap;
import com.kaola.demo.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class IndexController {


    @Autowired
    private IndexService indexService;

    @GetMapping("/index")
    public ResultMap index(HttpSession session){
        User user = (User)session.getAttribute("user");
        return   indexService.getAllGoods(user.getId());
    }
}
