package com.kaola.demo.service.serviceImpl;

import com.google.common.collect.Sets;
import com.kaola.demo.mapper.ContentMapper;
import com.kaola.demo.mapper.OrderRecordMapper;
import com.kaola.demo.meta.Content;
import com.kaola.demo.meta.OrderRecord;
import com.kaola.demo.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {


    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private OrderRecordMapper recordMapper;

    @Override
    public List<Content> getAllGoods(int userId,String type) {
        List<Content> allGoods = contentMapper.getAllGoods();
        if("".equals(type)){
          return  allGoods;
        }
        HashSet<Integer> set = Sets.newHashSet();
        List<OrderRecord> recordList = recordMapper.getOrderRecordByUserId(userId);
        for (OrderRecord record : recordList) {
            set.add(record.getGoodsId());
        }
        List<Content> goods = new ArrayList<>();
        //已购买
        if("1".equals(type) && userId > 0){
            allGoods.forEach(item->{
             if(set.contains(item.getId())){
                 goods.add(item);
             }
            });
          return goods;
        }
        //未购买
        if("2".equals(type) && userId > 0){
            allGoods.forEach(item->{
                if(!set.contains(item.getId())){
                    goods.add(item);
                }
            });
            return goods;
        }
        return allGoods;
    }
}
