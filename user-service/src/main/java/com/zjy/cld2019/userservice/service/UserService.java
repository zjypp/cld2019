package com.zjy.cld2019.userservice.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zjy.cld2019.common.utils.MD5Utils;
import com.zjy.cld2019.common.utils.PwdUtil;
import com.zjy.cld2019.common.utils.StringUtil;
import com.zjy.cld2019.common.utils.UUIDUtil;
import com.zjy.cld2019.userservice.dao.UserLoginLogMapper;
import com.zjy.cld2019.userservice.dao.UserMapper;
import com.zjy.cld2019.userservice.model.User;
import com.zjy.cld2019.userservice.model.UserLoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserLoginLogMapper userLoginLogMapper;

    @DS("read1")
    public User getUserById(Integer id){
        return userMapper.getUserById(id);
    }

    public User getUserByUserid(String userId){
        return userMapper.getUserByUserId(userId);
    }

    public User getUserByPhone(String phone){
        return userMapper.getUserByPhone(phone);
    }

    /**
     * register user
     * @param user
     * @return
     */
    public User registerUser(User user){

        if(StringUtil.isNotEmpty(user.getPhoneNum())){
            // the phone is exits;
            User r = userMapper.getUserByPhone(user.getPhoneNum());
            if(r!=null){
                return null;
            }
        }

        user.setUserPwd(PwdUtil.generateRegPwd(user.getUserPwd()));
        user.setUserId(UUIDUtil.getUUID());
        int count = userMapper.insertUser(user);//仍然返回的是影响的行数
        if(count ==1) {
             return user;
        }else {
            return null;
        }
    }

    /**
     * login
     * @param user
     * @return
     */
    public boolean loginUser(String phone, String password){
        String pwd = PwdUtil.generateRegPwd(password);
        User u = userMapper.login(phone,pwd);
        if(u!=null){
            User user = userMapper.getUserByPhone(phone);
            UserLoginLog userLoginLog = new UserLoginLog();
            userLoginLog.setUserId(user.getUserId());
            userLoginLog.setLoginDevice("app");
            userLoginLog.setDeviceVersion("0.1.1");
            userLoginLogMapper.insertLog(userLoginLog);
            return true;
        }
        else{
            return false;
        }
    }
}
