package com.kaola.demo.controller;

import com.google.common.collect.Lists;
import com.kaola.demo.enums.CodeMsg;
import com.kaola.demo.meta.Content;
import com.kaola.demo.meta.OrderRecord;
import com.kaola.demo.meta.ShoppingCart;
import com.kaola.demo.model.ResultMap;
import com.kaola.demo.service.ContentService;
import com.kaola.demo.service.OrderRecordService;
import com.kaola.demo.service.ShoppingCartService;
import com.kaola.demo.vo.ShoppingCartVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author: li ying
 * @Date: 2019/2/10 20:33
 */
@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private OrderRecordService orderRecordService;

    @GetMapping("/addShoppingCart")
    public ResultMap addShoppingCart(ShoppingCart shoppingCart){
        ShoppingCart cart = shoppingCartService.addShoppingCart(shoppingCart);
        return ResultMap.genResultMap(CodeMsg.SUCCESS,cart);
    }

    @GetMapping("/addNum")
    public ResultMap addNum(ShoppingCart shoppingCart){
        ShoppingCart cart = shoppingCartService.updateShoppingCart(shoppingCart);
        return ResultMap.genResultMap(CodeMsg.SUCCESS,cart);
    }

    @GetMapping("/decreaseNum")
    public ResultMap decreaseNum(ShoppingCart shoppingCart){
        ShoppingCart cart = shoppingCartService.updateShoppingCart(shoppingCart);
        return ResultMap.genResultMap(CodeMsg.SUCCESS,cart);
    }

    @GetMapping("/updateShoppingCart")
    public ResultMap updateShoppingCart(ShoppingCart shoppingCart){
        if(shoppingCart.getNum() <= 0){
            return ResultMap.genResultMap(CodeMsg.ERROR,"商品数量不能小于0");
        }
        ShoppingCart cart = shoppingCartService.updateShoppingCart(shoppingCart);
        return ResultMap.genResultMap(CodeMsg.SUCCESS,cart);
    }

    @GetMapping("/getShoppingCart")
    public ResultMap getShoppingCart(int userId){
        List<ShoppingCart> cart = shoppingCartService.getShoppingCartByUserId(userId);
        List<ShoppingCartVo> carts = Lists.newArrayList();
        if(!cart.isEmpty()){
            for(ShoppingCart cart1 : cart){
                Content content = contentService.getContentById(cart1.getContentId());
                ShoppingCartVo shoppingCartVo = new ShoppingCartVo();
                BeanUtils.copyProperties(cart1,shoppingCartVo);
                shoppingCartVo.setPrice(content.getPrice());
                shoppingCartVo.setName(content.getTitle());
                carts.add(shoppingCartVo);
            }
        }
        return ResultMap.genResultMap(CodeMsg.SUCCESS,carts);
    }

    @GetMapping("/purchase")
    public ResultMap purchase(int userId){
        List<ShoppingCart> cart = shoppingCartService.getShoppingCartByUserId(userId);
        if(cart.isEmpty()){
            return ResultMap.genResultMap(CodeMsg.ERROR,"购物车为空，无法购买");
        }else {
            //清空购物车，添加购买记录
            for(ShoppingCart cart1 : cart){
                OrderRecord record = new OrderRecord();
                String price = contentService.getContentById(cart1.getContentId()).getPrice();
                record.setUserId(userId);
                record.setCount(cart1.getNum());
                record.setGoodsId(cart1.getContentId());
                record.setPrice(price);
                record.setOrderTime(new Date());
                orderRecordService.addOrderRecord(record);
                shoppingCartService.deleteShoppingCart(cart1.getId());
            }

        }
        return ResultMap.genResultMap(CodeMsg.SUCCESS,cart);
    }



    @GetMapping("/deleteShoppingCart")
    public ResultMap deleteShoppingCart(int id){
        Boolean aBoolean = shoppingCartService.deleteShoppingCart(id);
        if(aBoolean) {
            return ResultMap.genResultMap(CodeMsg.SUCCESS);
        }else {
            return ResultMap.genResultMap(CodeMsg.ERROR,"删除失败");
        }
    }

}
