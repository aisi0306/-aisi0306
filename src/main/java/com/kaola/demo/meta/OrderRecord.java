package com.kaola.demo.meta;

import lombok.Data;

import java.util.Date;

@Data
public class OrderRecord {

    private int id;

    private Date orderTime;

    private int count;

    private String price;

    private int goodsId;

    private int userId;


}
