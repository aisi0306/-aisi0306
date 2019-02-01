package com.kaola.demo.controller;

import com.kaola.demo.enums.CodeMsg;
import com.kaola.demo.meta.Content;
import com.kaola.demo.model.ResultMap;
import com.kaola.demo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: li ying
 * @Date: 2019/2/1 21:37
 */
@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/getContent")
    public ResultMap getContent(int id){
        Content content = contentService.getContentById(id);
        ResultMap resultMap = new ResultMap();
        resultMap.setData(content);
        resultMap.setCode(CodeMsg.SUCCESS.getCode());
        return resultMap;
    }

    @GetMapping("/updateContent")
    public ResultMap updateContent(Content content){
        contentService
    }


}
