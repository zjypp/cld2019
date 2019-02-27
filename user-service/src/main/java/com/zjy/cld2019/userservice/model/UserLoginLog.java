package com.zjy.cld2019.userservice.model;

import com.zjy.cld2019.common.model.BaseModel;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/25 5:10 PM
 * @Version: 1.0
 * @Des：用户登陆日志记录
 */
public class UserLoginLog extends BaseModel {

    private String userId;
    private String loginTime;
    private String loginDevice;

    public String getLoginDevice() {
        return loginDevice;
    }

    public void setLoginDevice(String loginDevice) {
        this.loginDevice = loginDevice;
    }

    private String deviceVersion;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }



    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion;
    }
}
