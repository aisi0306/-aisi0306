package com.kaola.demo.service;

import com.kaola.demo.meta.Content;
import com.kaola.demo.model.ResultMap;
import com.kaola.demo.vo.ContentVo;

import java.util.List;

public interface IndexService {

     List<ContentVo> getAllGoods(int userId, String type);
}
