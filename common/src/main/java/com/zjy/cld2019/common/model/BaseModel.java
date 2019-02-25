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
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
