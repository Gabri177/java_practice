package com.yugao.screen.dao;

import com.yugao.screen.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user_info where username = #{username} and password = sha1(#{password})")
    User checkExistance(@Param("username") String username,@Param("password") String password);

    int insertUser(User user);
}
