package com.kaola.demo.service;

import com.kaola.demo.meta.OrderRecord;
import com.kaola.demo.vo.OrderRecordVo;

import java.util.List;

/**
 * @Author: li ying
 * @Date: 2019/1/17 20:53
 */
public interface OrderRecordService {

    List<OrderRecordVo> getOrderRecordByUserId(int userId);

    OrderRecord addOrderRecord(OrderRecord orderRecord);
}
