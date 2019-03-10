package com.zjy.cld2019.userservice.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zjy.cld2019.userservice.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

@Mapper
public interface UserMapper {

    @DS("read1")
    @Select("select * from user where id = #{id}")
    User getUserById(Integer id);

    @Select("select * from user where phone_num = #{phone}")
    User getUserByPhone(String phone);

    @Select("select * from user where user_id = #{userId}")
    User getUserByUserId(String userId);

    @Select("select * from user where phone_num = #{phone} and user_pwd = #{pwd}")
    User login(@Param("phone") String phone, @Param("pwd") String pwd);
    
    @Insert("insert into user(phone_num,user_id,user_name,user_pwd,real_name,age) values(#{phoneNum},#{userId},#{userName},#{userPwd},#{realName},#{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);
}
