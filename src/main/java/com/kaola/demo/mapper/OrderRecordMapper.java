package com.kaola.demo.mapper;

import com.kaola.demo.meta.OrderRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderRecordMapper {

    @Select("SELECT * FROM order_record WHERE user_id=#{userId}")
    List<OrderRecord> getOrderRecordByUserId(int userId);

    @Select("SELECT * FROM order_record WHERE goods_id=#{goodsId}")
    List<OrderRecord> getOrderRecordByGoodId(int goodsId);

    @Select("SELECT * FROM order_record WHERE goods_id=#{goodsId} AND user_id=#{userId}")
    OrderRecord getOrderRecord(@Param("goodsId") int goodsId,@Param("userId") int userId);

    @Insert("INSERT INTO order_record(order_time,price,count,goods_id,user_id) " +
            "VALUES(#{orderTime},#{price},#{count},#{goodsId},#{userId})")
    Integer addOrderRecord(OrderRecord orderRecord);
}
