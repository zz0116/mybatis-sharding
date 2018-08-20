package com.zyz.demo.sharding.mapper;

import com.zyz.demo.sharding.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select uid, name, pwd from user where name = #{username}")
    User getUserByName(@Param("username") String username);

    @Insert("insert into user (uid, name, pwd) values (#{user.uid}, #{user.username}, #{user.password})")
    boolean addUser(@Param("user") User user);
}
