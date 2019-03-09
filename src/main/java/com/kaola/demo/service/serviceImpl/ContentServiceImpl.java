package com.kaola.demo.service.serviceImpl;

import com.google.common.collect.Lists;
import com.kaola.demo.mapper.ContentMapper;
import com.kaola.demo.mapper.OrderRecordMapper;
import com.kaola.demo.meta.Content;
import com.kaola.demo.meta.OrderRecord;
import com.kaola.demo.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: li ying
 * @Date: 2019/1/21 19:25
 */
@Service
public class ContentServiceImpl implements ContentService {

    private static Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);


    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private OrderRecordMapper recordMapper;
    @Override
    public Content addContent(Content content) {
        Integer result = 0;
        try{
           result   = contentMapper.addContent(content);
        }catch (Exception e){
            return null;
        }
        if(result == 0){
            return null;
        }
        return content;
    }

    @Override
    public Content updateContent(Content content) {
        Integer result = 0;
        try{
            result = contentMapper.updateContent(content);
        }catch (Exception e){
            return null;
        }
        if(result == 0){
            return null;
        }
        return content;
    }

    /**
     * 可以删除未出售的商品
     * @param id
     * @return
     */
    @Override
    public Boolean deleteContent(int id) {
        Content goodsById = contentMapper.getGoodsById(id);
        List<OrderRecord> records = Lists.newArrayList();
        if(goodsById != null){
             records = recordMapper.getOrderRecordByGoodId(id);
        }
        Boolean result = false;
        if(records.isEmpty()){
            try{
                result = contentMapper.deleteContent(id);
            }catch (Exception e){
                logger.debug("id为"+id+"的内容删除失败");
            }
        }
        return result;
    }

    @Override
    public Content getContentById(int id) {
        Content content = contentMapper.getGoodsById(id);
        return content;
    }
}
