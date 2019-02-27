package com.kaola.demo.service.serviceImpl;

import com.kaola.demo.mapper.ShoppingCartMapper;
import com.kaola.demo.meta.ShoppingCart;
import com.kaola.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: li ying
 * @Date: 2019/2/24 15:23
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Override
    public ShoppingCart addShoppingCart(ShoppingCart shoppingCart) {
        List<ShoppingCart> carts = shoppingCartMapper.getByUserId(shoppingCart.getUserId());
        if(carts.isEmpty() || (!carts.contains(shoppingCart))){
            ShoppingCart cart = shoppingCartMapper.addShoppingCart(shoppingCart);
            return cart;
        }else {
            List<ShoppingCart> cartList = shoppingCartMapper.getByUserId(shoppingCart.getUserId());
            int num = shoppingCart.getNum();
            for (ShoppingCart cart: cartList) {
                if(cart.equals(shoppingCart)){
                    num = num+cart.getNum();
                }
            }
            shoppingCart.setNum(num);
            ShoppingCart cart = shoppingCartMapper.updateShoppingCart(shoppingCart);
            return cart;
        }
    }

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
        ShoppingCart cart = shoppingCartMapper.updateShoppingCart(shoppingCart);
        return cart;
    }

    @Override
    public Boolean deleteShoppingCart(int id) {
        Boolean aBoolean = shoppingCartMapper.deleteshoppingCart(id);
        return aBoolean;
    }

    @Override
    public ShoppingCart getShoppingCartById(int id) {
        ShoppingCart cart = shoppingCartMapper.getById(id);
        return cart;
    }

    @Override
    public List<ShoppingCart> getShoppingCartByUserId(int userId) {
        List<ShoppingCart> carts = shoppingCartMapper.getByUserId(userId);
        return carts;
    }
}
