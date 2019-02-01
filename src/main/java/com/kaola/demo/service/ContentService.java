package com.kaola.demo.service;

import com.kaola.demo.meta.Content;

/**
 * @Author: li ying
 * @Date: 2019/1/18 21:22
 */
public interface ContentService {


    Content addContent(Content content);

    Content updateContent(Content content);

    Boolean deleteContent(int id);

    Content getContentById(int id);
}
