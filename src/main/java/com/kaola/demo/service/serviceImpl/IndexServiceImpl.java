package com.kaola.demo.service.serviceImpl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.kaola.demo.dao.ContentMapper;
import com.kaola.demo.dao.OrderRecordMapper;
import com.kaola.demo.enums.CodeMsg;
import com.kaola.demo.enums.GoodType;
import com.kaola.demo.meta.Content;
import com.kaola.demo.meta.OrderRecord;
import com.kaola.demo.model.ResultMap;
import com.kaola.demo.service.IndexService;
import com.kaola.demo.vo.ContentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {


    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private OrderRecordMapper recordMapper;

    @Override
    public ResultMap getAllGoods(int userId) {
        List<Content> contents = contentMapper.getAllGoods();
        List<ContentVo> contentVos = Lists.newArrayList();
        ContentVo contentVo ;
        HashSet<Integer> set = Sets.newHashSet();
        if (userId > 0) {
            List<OrderRecord> recordList = recordMapper.getOrderRecordByUserId(userId);
            for (OrderRecord record : recordList) {
                set.add(record.getGoodsId());
            }
        }

        for (Content content1 : contents) {
            contentVo = new ContentVo();
            BeanUtils.copyProperties(content1, contentVo);

            if (set.contains(contentVo.getId())) {
                contentVo.setType(GoodType.SELLED.getCode());
            } else {
                contentVo.setType(GoodType.SELLING.getCode());
            }
            contentVos.add(contentVo);
        }
        ResultMap resultMap = ResultMap.genResultMap(CodeMsg.SUCCESS,contentVos);
        return resultMap;
    }
}
