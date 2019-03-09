package com.kaola.demo.service;

import com.kaola.demo.meta.ShoppingCart;

import java.util.List;

/**
 * @Author: li ying
 * @Date: 2019/2/24 15:22
 */
public interface ShoppingCartService {
    ShoppingCart addShoppingCart(ShoppingCart shoppingCart);

    Boolean updateShoppingCart(ShoppingCart shoppingCart);

    Boolean deleteShoppingCart(int id);

    ShoppingCart getShoppingCartById(int id);

    List<ShoppingCart> getShoppingCartByUserId(int userId);
}
