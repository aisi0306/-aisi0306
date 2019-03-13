package com.kaola.demo.controller;

import com.kaola.demo.enums.CodeMsg;
import com.kaola.demo.meta.Content;
import com.kaola.demo.meta.OrderRecord;
import com.kaola.demo.meta.User;
import com.kaola.demo.model.ResultMap;
import com.kaola.demo.service.ContentService;
import com.kaola.demo.service.OrderRecordService;
import com.kaola.demo.vo.ContentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: li ying
 * @Date: 2019/2/1 21:37
 */
@Controller
public class ContentController {

    @Resource
    private ContentService contentService;

    @Autowired
    private OrderRecordService orderRecordService;

    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public ModelAndView pul() {
        return new ModelAndView("public");
    }

    @GetMapping("/getContent")
    public ModelAndView getContent(int id,ModelMap map,HttpSession session){
        Content content = contentService.getContentById(id);
        OrderRecord record = orderRecordService.getOrderRecordByUidAndCid(1, content.getId());
        ContentVo contentVo = new ContentVo();
        BeanUtils.copyProperties(content, contentVo);
        if(record !=null) {
            contentVo.setPrePrice(record.getPrice());
        }
        map.addAttribute("product", contentVo);
        return new ModelAndView("detail");
    }

    @PostMapping("/publicSubmit")
    public ModelAndView addContent(@RequestParam("title") String title,
                                   @RequestParam("remark") String summary,
                                   @RequestParam("price") Double price,
                                   @RequestParam("pictureUrl") String image,
                                   @RequestParam("text") String detail, ModelMap map){

       Content content = new Content();
       content.setPictureUrl(image);
       content.setPrice(String.valueOf(price));
       content.setRemark(summary);
       content.setTitle(title);
       content.setText(detail);
        Content addContent = contentService.addContent(content);
        if( addContent != null) {
            map.addAttribute("product", addContent);
            return new ModelAndView("redirect:/");
        }else {
            map.addAttribute("error", ResultMap.genResultMap(CodeMsg.ERROR,"发布失败"));
            return new ModelAndView("error");
        }
    }

    @PostMapping("/updateContent")
    public ModelAndView updateContent(@RequestParam("id") int id,
                                      @RequestParam("title") String title,
                                      @RequestParam("summary") String summary,
                                      @RequestParam("price") Double price,
                                      @RequestParam("image") String image,
                                      @RequestParam("detail") String detail, ModelMap map){
        Content content = new Content();
        content.setId(id);
        content.setPictureUrl(image);
        content.setPrice(String.valueOf(price));
        content.setRemark(summary);
        content.setTitle(title);
        content.setText(detail);
        Content updateContent = contentService.updateContent(content);
        if(updateContent != null) {
            map.addAttribute("product", updateContent);
            return new ModelAndView("editSubmit");
        }else {
            map.addAttribute("error", ResultMap.genResultMap(CodeMsg.ERROR,"更新失败"));
            return new ModelAndView("error");
        }
    }

    @GetMapping("/getUpdateContent")
    public ModelAndView getUpdateContent(@RequestParam("id") Integer cid,ModelMap map){
        Content updateContent = contentService.getContentById(cid);
        map.addAttribute("product",updateContent);
        return new ModelAndView("edit");
    }

    @GetMapping("/deleteContent")
    public ModelAndView deleteContent(int id,ModelMap map){
       Boolean result = contentService.deleteContent(id);
        if(!result){
            map.addAttribute("error",ResultMap.genResultMap(CodeMsg.ERROR,"无法删除以及出售的商品"));
            return new ModelAndView("error");
        }else {
            return  new ModelAndView("redirect:/");
        }

    }

    @PostMapping("/upload")
    @ResponseBody
    public ResultMap uploadPic(HttpSession session, HttpServletRequest request){
        Map<String, Object> result = new HashMap<String, Object>();
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

            return ResultMap.genResultMap(CodeMsg.SUCCESS,"",imgUrl);
        } catch (Exception e) {
            return ResultMap.genResultMap(CodeMsg.ERROR);
        }
    }

    /*public String checkContent(Content content){
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
    }*/
}
