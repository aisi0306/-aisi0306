package com.kaola.demo.mapper;

import com.kaola.demo.meta.Content;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ContentMapper {


    @Select("SELECT * FROM content WHERE id=#{id}")
    Content getGoodsById(int id);

    @Select("SELECT * FROM content")
    List<Content> getAllGoods();

    @Insert("INSERT INTO content(title,price,picture_url,remark,text) " +
            "VALUES(#{title},#{price},#{pictureUrl},#{remark},#{text})")
    Integer addContent(Content content);

    @Update("UPDATE content SET title=#{title},price=#{price},picture_url=#{pictureUrl}" +
            ",remark=#{remark},text=#{text} WHERE id=#{id}")
    Integer updateContent(Content content);

    @Delete("DELETE * FROM content WHERE id=#{id}")
    Boolean deleteContent(int id);

}
