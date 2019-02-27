package com.kaola.demo.vo;

import com.kaola.demo.meta.ShoppingCart;
import lombok.Data;

/**
 * @Author: li ying
 * @Date: 2019/2/25 20:36
 */
@Data
public class ShoppingCartVo extends ShoppingCart {
    private String name;

    private String price;
}
