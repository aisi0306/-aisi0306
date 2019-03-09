package com.kaola.demo.service;

import com.kaola.demo.meta.Content;
import com.kaola.demo.model.ResultMap;

import java.util.List;

public interface IndexService {

     List<Content> getAllGoods(int userId, String type);
}
