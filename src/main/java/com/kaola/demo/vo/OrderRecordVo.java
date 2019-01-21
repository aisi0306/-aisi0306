package com.kaola.demo.vo;

import com.kaola.demo.meta.OrderRecord;
import lombok.Data;

/**
 * @Author: li ying
 * @Date: 2019/1/17 21:14
 */
@Data
public class OrderRecordVo extends OrderRecord {

    private String title;

    private String pictureUrl;

    private String total;
}
