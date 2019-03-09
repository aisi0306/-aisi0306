package com.kaola.demo.controller;

import com.kaola.demo.meta.Content;
import com.kaola.demo.meta.User;
import com.kaola.demo.model.ResultMap;
import com.kaola.demo.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private IndexService indexService;

    @GetMapping({"/index","/"})
    public ModelAndView index(HttpSession session,
                              @RequestParam(value = "type", defaultValue = "") String type,
                              ModelMap map){
        User user = (User)session.getAttribute("user");
        int userId;
        userId = user == null? 0: user.getId();
        List<Content> allGoods = indexService.getAllGoods(userId, type);
        map.addAttribute("products",allGoods);
        return new ModelAndView("index");
        //return   indexService.getAllGoods(user.getId(),type);
    }
}
