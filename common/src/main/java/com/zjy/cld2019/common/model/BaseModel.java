package com.zjy.cld2019.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Jason Zhang
 * @Date: 2019/1/25 3:34 PM
 * @Version 1.0
 * 所有model的父类
 *
 */
public class BaseModel implements Serializable {
    private Integer id;
    private String createTime;
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
