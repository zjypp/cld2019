package com.zjy.cld2019.userservice.dao;

import com.zjy.cld2019.userservice.model.UserLoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/25 5:16 PM
 * @Version: 1.0
 * @Des：
 */
@Mapper
public interface UserLoginLogMapper {

    @Insert("insert into `user_login_log` ( `user_id`, `login_device`, `device_version`) values ( #{userId}, #{loginDevice}, #{deviceVersion})")
    int insertLog(UserLoginLog userLoginLog);
}
