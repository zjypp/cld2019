package com.zjy.cld2019.userservice.controller;

import com.zjy.cld2019.common.rest.RestResponse;
import com.zjy.cld2019.common.rest.RestResponseBuilder;
import com.zjy.cld2019.common.rest.error.ServiceError;
import com.zjy.cld2019.common.utils.PhoneUtil;
import com.zjy.cld2019.common.utils.StringUtil;
import com.zjy.cld2019.userservice.error.UserServiceError;
import com.zjy.cld2019.userservice.model.User;
import com.zjy.cld2019.userservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api("UserServiceApi")
public class UserController {

    @Autowired
    UserService userService;



    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "test";
    }

    @ApiOperation(value="get one user info", notes="")
    @RequestMapping(value = "/getuser",method = RequestMethod.GET)
    public RestResponse<User> getUser(String id){
        RestResponseBuilder restResponseBuilder = new RestResponseBuilder();
        User u = userService.getUserById(Integer.parseInt(id));
        if(u!=null){
            return restResponseBuilder.success(u);
        }else{
            return restResponseBuilder.fail(UserServiceError.SY0101);
        }
    }

    @ApiOperation(value="get one user info by phone", notes="")
    @RequestMapping(value = "/getuser",method = RequestMethod.GET)
    public RestResponse<User> getUserByPhone(String phone){
        RestResponseBuilder restResponseBuilder = new RestResponseBuilder();
        if(!PhoneUtil.isPhone(phone)){
            return restResponseBuilder.fail(UserServiceError.SY0105);
        }


        User u = userService.getUserByPhone(phone);
        if(u!=null){
            return restResponseBuilder.success(u);
        }else{
            return restResponseBuilder.fail(UserServiceError.SY0101);
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public RestResponse<User> registerUser(String phone,String username,String realName,String password,Integer age){
        RestResponseBuilder restResponseBuilder = new RestResponseBuilder();

        if(!PhoneUtil.isPhone(phone)){
            return restResponseBuilder.fail(UserServiceError.SY0105);
        }

        User user = new User();
        user.setPhoneNum(phone);
        user.setUserName(username);
        user.setRealName(realName);
        user.setUserPwd(password);
        user.setAge(age);

        User result = userService.registerUser(user);
        if(result !=null){
            return restResponseBuilder.success(user);
        }
        else{
            return restResponseBuilder.fail(UserServiceError.US020001);
        }
    }

    @RequestMapping(value = "/login",method =RequestMethod.POST)
    public RestResponse<Boolean> login(String phone,String password){
        RestResponseBuilder restResponseBuilder = new RestResponseBuilder();
        //the phone number is error
        if(!PhoneUtil.isPhone(phone)){
            return restResponseBuilder.fail(UserServiceError.SY0105);
        }
        // the password is valiate
        if(StringUtil.isEmpty(password) && password.length() <6){
            return restResponseBuilder.fail(UserServiceError.SY0105);
        }

        boolean result =userService.loginUser(phone,password);
        if(result){
            return restResponseBuilder.success(true);
        }else{
            return restResponseBuilder.fail(UserServiceError.US020004);
        }

    }
}
