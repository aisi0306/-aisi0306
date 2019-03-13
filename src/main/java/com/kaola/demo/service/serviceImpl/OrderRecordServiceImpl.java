package com.kaola.demo.service.serviceImpl;

import com.google.common.collect.Lists;
import com.kaola.demo.mapper.ContentMapper;
import com.kaola.demo.mapper.OrderRecordMapper;
import com.kaola.demo.meta.Content;
import com.kaola.demo.meta.OrderRecord;
import com.kaola.demo.service.OrderRecordService;
import com.kaola.demo.vo.OrderRecordVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: li ying
 * @Date: 2019/1/17 20:56
 */
@Service
public class OrderRecordServiceImpl implements OrderRecordService {

    @Autowired
    private OrderRecordMapper orderRecordMapper;

    @Autowired
    private ContentMapper contentMapper;
   /**
   * @Description:
   * @Param:
   * @return:
   * @Author: li ying
   * @Date: 2019/1/17
   */
    @Override
    public List<OrderRecordVo> getOrderRecordByUserId(int userId) {
        List<OrderRecord> records = orderRecordMapper.getOrderRecordByUserId(userId);

        List<OrderRecordVo> recordVos = Lists.newArrayList();
        OrderRecordVo recordVo;
        Double total = 0.0;
        for(OrderRecord record : records){
            recordVo = new OrderRecordVo();
            BeanUtils.copyProperties(record,recordVo);
            Content content = contentMapper.getGoodsById(record.getGoodsId());
            recordVo.setPictureUrl(content.getPictureUrl());
            recordVo.setTitle(content.getTitle());
            total+=Double.valueOf(record.getPrice())*record.getCount();
            recordVo.setTotal(String.valueOf(total));
            recordVos.add(recordVo);
        }
        return recordVos;
    }

    @Override
    public OrderRecord addOrderRecord(OrderRecord orderRecord) {
        Integer result = 0;
        try{
            result   = orderRecordMapper.addOrderRecord(orderRecord);
        }catch (Exception e){
            return null;
        }
        if(result == 0){
            return null;
        }
        return orderRecord;
    }

    @Override
    public OrderRecord getOrderRecordByUidAndCid(int userId, int goodsId) {

        return orderRecordMapper.getOrderRecord(goodsId,userId);
    }
}
