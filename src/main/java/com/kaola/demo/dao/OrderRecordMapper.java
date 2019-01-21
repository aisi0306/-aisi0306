package com.kaola.demo.dao;

import com.kaola.demo.meta.OrderRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderRecordMapper {

    @Select("SELECT * FROM order_record WHERE userId=#{userId}")
    List<OrderRecord> getOrderRecordByUserId(int userId);

    @Insert("INSERT INTO order_record(order_time,price,count,goods_id,user_id) " +
            "VALUES(#{orderTime},#{price},#{count},#{goodsId},#{userId})")
    OrderRecord addOrderRecord(OrderRecord orderRecord);
}
