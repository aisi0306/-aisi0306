package com.kaola.demo.controller;

import com.kaola.demo.enums.CodeMsg;
import com.kaola.demo.model.ResultMap;
import com.kaola.demo.service.OrderRecordService;
import com.kaola.demo.vo.OrderRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: li ying
 * @Date: 2019/1/17 20:51
 */
@RestController
public class OrderRecordController {

    @Autowired
    private OrderRecordService orderRecordService;

    @GetMapping("/orderRecord")
    public ResultMap getOrderRecord(int userId){

        List<OrderRecordVo> recordVos = orderRecordService.getOrderRecordByUserId(userId);
        ResultMap resultMap = ResultMap.genResultMap(CodeMsg.SUCCESS);
        resultMap.setData(recordVos);
        return resultMap;
    }

}
