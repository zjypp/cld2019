package com.zjy.cld2019.orderservice.client.userServiceClient;

import com.zjy.cld2019.common.rest.RestResponse;
import com.zjy.cld2019.common.utils.PhoneUtil;
import com.zjy.cld2019.common.utils.StringUtil;
import com.zjy.cld2019.orderservice.client.userServiceClient.model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/1 10:31 AM
 * @Version: 1.0
 * @Des：
 */
@FeignClient(value = "user-service")
public interface UserServiceClient {

    @RequestMapping(value = "/user/getuser",method = RequestMethod.POST)
    RestResponse<User> getUser(@RequestParam("id") String Id);

    @RequestMapping(value = "/user/getuserbyuserid",method = RequestMethod.POST)
    RestResponse<User> getUserByUserId(@RequestParam("userId") String userId);

    @RequestMapping(value = "/user/getuserbyphone",method = RequestMethod.POST)
    RestResponse<User> getUserByPhone(@RequestParam("phone") String phone);

}
