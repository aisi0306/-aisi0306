package com.kaola.demo.dao;

import com.kaola.demo.meta.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id=#{id}")
    User getUserById(int id);

    @Select("SELECT * FROM user WHERE account=#{account}")
    User getUserByAccount(String account);
}
