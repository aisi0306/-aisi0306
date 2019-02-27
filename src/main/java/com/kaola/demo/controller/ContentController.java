package com.kaola.demo.controller;

import com.kaola.demo.enums.CodeMsg;
import com.kaola.demo.meta.Content;
import com.kaola.demo.model.ResultMap;
import com.kaola.demo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: li ying
 * @Date: 2019/2/1 21:37
 */
@RestController
public class ContentController {

    @Resource
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

    @PostMapping("/upload")
    public ResultMap uploadPic(HttpServletRequest request){
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 将文件放置位置设置为静态资源目录image中
            String logoPathDir = request.getSession().getServletContext().getRealPath("/") +"/image/";
            File logoSaveFile = new File(logoPathDir);
            if (!logoSaveFile.exists()){
                logoSaveFile.mkdirs();
            }
            // 页面控件的文件流
            MultipartFile multipartFile = multipartRequest.getFile("file");
            // 获取文件的后缀
            String suffix = multipartFile.getOriginalFilename().substring(
                    multipartFile.getOriginalFilename().lastIndexOf("."));
            // 使用UUID生成文件名称
            String imageName = UUID.randomUUID().toString() + suffix;

            // 拼成完整的文件保存路径加文件
            String fileName = logoPathDir + imageName;
            File file = new File(fileName);
            try {
                multipartFile.transferTo(file);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 生成图片网址
            String imgUrl = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/image/" + imageName;

            return ResultMap.genResultMap(CodeMsg.SUCCESS,imgUrl);
        } catch (Exception e) {
            return ResultMap.genResultMap(CodeMsg.ERROR);
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
