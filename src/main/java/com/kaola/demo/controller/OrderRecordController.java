package com.kaola.demo.controller;

import com.kaola.demo.enums.CodeMsg;
import com.kaola.demo.meta.Content;
import com.kaola.demo.meta.User;
import com.kaola.demo.model.ResultMap;
import com.kaola.demo.service.ContentService;
import com.kaola.demo.service.OrderRecordService;
import com.kaola.demo.vo.OrderRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: li ying
 * @Date: 2019/1/17 20:51
 */
@Controller
public class OrderRecordController {

    @Resource
    private OrderRecordService orderRecordService;

    @Resource
    private ContentService contentService;

    @GetMapping("/orderRecord")
    public ModelAndView getOrderRecord(ModelMap map,
                                       HttpSession session){
        User user = (User)session.getAttribute("user");
        int userId = user.getId();
        List<OrderRecordVo> recordVos = orderRecordService.getOrderRecordByUserId(userId);
        map.addAttribute("buyList",recordVos);
        return new ModelAndView("orderRecord");
    }

    @GetMapping("/snapshot")
    public ModelAndView snapshot(@RequestParam("id") Integer id,
                                 ModelMap map){
        Content content = contentService.getContentById(id);
        map.addAttribute("product",content);
        return new ModelAndView("snapshot");
    }

}
