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
        return ResultMap.genResultMap(CodeMsg.SUCCESS,content);
    }

    @GetMapping("/addContent")
    public ResultMap addContent(Content content){
        String s = checkContent(content);
        if( s != null){
            return ResultMap.genResultMap(CodeMsg.CONTENT_ERROR,s);
        }
        Content addContent = contentService.addContent(content);
        if(addContent == null){
            return ResultMap.genResultMap(CodeMsg.ERROR);
        }else {
            return ResultMap.genResultMap(CodeMsg.SUCCESS,addContent);
        }

    }

    @GetMapping("/updateContent")
    public ResultMap updateContent(Content content){
        String s = checkContent(content);
        if( s != null){
            return ResultMap.genResultMap(CodeMsg.CONTENT_ERROR,s);
        }
        Content updateContent = contentService.updateContent(content);
        if(updateContent == null){
            return ResultMap.genResultMap(CodeMsg.ERROR);
        }else {
           return ResultMap.genResultMap(CodeMsg.SUCCESS,updateContent);
        }

    }

    @GetMapping("/deleteContent")
    public ResultMap deleteContent(int id){
       Boolean result = contentService.deleteContent(id);
        if(!result){
            return ResultMap.genResultMap(CodeMsg.ERROR);
        }else {
            return ResultMap.genResultMap(CodeMsg.SUCCESS);
        }

    }



    public String checkContent(Content content){
        if(content.getTitle().length()<2 || content.getTitle().length() >80){
            return "标题长度要在2到80字符之内";
        }
        if(content.getRemark().length()<2 || content.getRemark().length() >140){
            return "摘要长度要在2到140字符之内";
        }
        if(content.getText().length()<2 || content.getText().length() >1000){
            return "正文长度要在2到1000字符之内";
        }
        return null;
    }
}
