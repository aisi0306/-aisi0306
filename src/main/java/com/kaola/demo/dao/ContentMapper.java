package com.kaola.demo.dao;

import com.kaola.demo.meta.Content;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ContentMapper {


    @Select("SELECT * FROM content WHERE id=#{id}")
    Content getGoodsById(int id);

    @Select("SELECT * FROM content")
    List<Content> getAllGoods();

    @Insert("INSERT INTO content(title,price,picture_url,remark,text) " +
            "VALUES(#{title},#{price},#{pictureUrl},#{remark},#{text})")
    Content addContent(Content content);

    @Update("UPDATE content SET title=#{title},price=#{price},picture_url=#{pictureUrl}" +
            ",remark=#{remark},text=#{text} WHERE id=#{id}")
    Content updateContent(Content content);

}
