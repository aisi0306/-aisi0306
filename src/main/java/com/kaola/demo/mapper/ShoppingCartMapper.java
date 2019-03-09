package com.kaola.demo.mapper;

import com.kaola.demo.meta.Content;
import com.kaola.demo.meta.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: li ying
 * @Date: 2019/2/24 15:11
 */
@Mapper
public interface ShoppingCartMapper {
    @Select("SELECT * FROM shopping_cart WHERE id=#{id}")
    ShoppingCart getById(int id);

    @Select("SELECT * FROM shopping_cart WHERE user_Id=#{userId}")
    List<ShoppingCart> getByUserId(int userId);

    @Insert("INSERT INTO shopping_cart(user_id,content_id,num) " +
            "VALUES(#{userId},#{contentId},#{num})")
    Integer addShoppingCart(ShoppingCart shoppingCart);

    @Update("UPDATE shopping_cart SET user_id=#{userId},content_id=#{contentId},num=#{num} WHERE id=#{id}")
    Boolean updateShoppingCart(ShoppingCart shoppingCart);

    @Delete("DELETE  FROM shopping_cart WHERE id=#{id}")
    Boolean deleteshoppingCart(int id);

}
