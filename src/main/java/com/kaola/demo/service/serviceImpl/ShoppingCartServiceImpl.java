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
            Integer result = 0;
            try{
                result   = shoppingCartMapper.addShoppingCart(shoppingCart);
            }catch (Exception e){
                return null;
            }
            if(result == 0){
                return null;
            }
            return shoppingCart;
        }else {
            List<ShoppingCart> cartList = shoppingCartMapper.getByUserId(shoppingCart.getUserId());
            int num = shoppingCart.getNum();
            for (ShoppingCart cart: cartList) {
                if(cart.equals(shoppingCart)){
                    num = num+cart.getNum();
                }
            }
            shoppingCart.setNum(num);
            Boolean aBoolean = shoppingCartMapper.updateShoppingCart(shoppingCart);
            if(!aBoolean){
                return null;
            }
            return shoppingCart;
        }
    }

    @Override
    public Boolean updateShoppingCart(ShoppingCart shoppingCart) {
        Boolean a = false;
        try {
           a= shoppingCartMapper.updateShoppingCart(shoppingCart);
        } catch (Exception e) {
            return false;
        }
        return a;
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
