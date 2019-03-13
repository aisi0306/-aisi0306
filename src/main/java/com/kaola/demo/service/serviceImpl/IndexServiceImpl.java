package com.kaola.demo.service.serviceImpl;

import com.google.common.collect.Sets;
import com.kaola.demo.mapper.ContentMapper;
import com.kaola.demo.mapper.OrderRecordMapper;
import com.kaola.demo.mapper.UserMapper;
import com.kaola.demo.meta.Content;
import com.kaola.demo.meta.OrderRecord;
import com.kaola.demo.meta.User;
import com.kaola.demo.service.IndexService;
import com.kaola.demo.vo.ContentVo;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<ContentVo> getAllGoods(int userId, String type) {
        List<Content> allGoods = contentMapper.getAllGoods();
        List<ContentVo> allGoodsVo = new ArrayList<>();
        HashSet<Integer> set = Sets.newHashSet();
        List<OrderRecord> recordList = recordMapper.getOrderRecordByUserId(1);
        for (OrderRecord record : recordList) {
            set.add(record.getGoodsId());
        }
        List<ContentVo> goods = new ArrayList<>();
        User user = userMapper.getUserById(userId);
        allGoods.stream().forEach(item ->{
            ContentVo contentVo = new ContentVo();
            BeanUtils.copyProperties(item,contentVo);
            allGoodsVo.add(contentVo);
        });
        if("".equals(type)) {
            if (userId > 0) {
                allGoodsVo.forEach(item -> {
                    if (set.contains(item.getId())) {
                        if (user.getUserType() == 0) {
                            item.setStatus("已购买");
                        } else {
                            item.setStatus("已售出" + cal(item.getId()) + "件");
                        }
                        goods.add(item);
                    }
                });
                return allGoodsVo;
            }
        }

        //已购买
        if("1".equals(type) && userId > 0){
            allGoodsVo.forEach(item->{
             if(set.contains(item.getId())){
                 if(user.getUserType() == 0){
                     item.setStatus("已购买");
                 }else {
                     item.setStatus("已售出"+cal(item.getId())+"件");
                 }
                 goods.add(item);
             }
            });
          return goods;
        }
        //未购买
        if("2".equals(type) && userId > 0){
            allGoodsVo.forEach(item->{
                if(!set.contains(item.getId())){
                    goods.add(item);
                }
            });
            return goods;
        }
        return allGoodsVo;
    }

    public int cal(int goodId) {
        List<OrderRecord> records = recordMapper.getOrderRecordByGoodId(goodId);
        int sum = 0;
        for (OrderRecord record : records) {
            sum += record.getCount();
        }
        return sum;
    }

}
