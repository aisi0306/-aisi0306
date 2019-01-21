package com.kaola.demo.service.serviceImpl;

import com.google.common.collect.Lists;
import com.kaola.demo.dao.ContentMapper;
import com.kaola.demo.dao.OrderRecordMapper;
import com.kaola.demo.meta.Content;
import com.kaola.demo.meta.OrderRecord;
import com.kaola.demo.service.OrderRecordService;
import com.kaola.demo.vo.OrderRecordVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @Author: li ying
 * @Date: 2019/1/17 20:56
 */
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
            recordVos.add(recordVo);
            total+=Double.valueOf(record.getPrice())*record.getCount();
        }
        return recordVos;
    }

    @Override
    public OrderRecord addOrderRecord(OrderRecord orderRecord) {
        OrderRecord record = orderRecordMapper.addOrderRecord(orderRecord);
        return record;
    }
}
