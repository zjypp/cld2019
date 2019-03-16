package com.zjy.cld2019.common;

import com.zjy.cld2019.common.utils.IdWorkerUtils;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/12 11:02 AM
 * @Version: 1.0
 * @Des：
 */
public  class TestMain {

    public static void main(String[] args){
        String b= IdWorkerUtils.getInstance().createUUID();
        String a=  IdWorkerUtils.getInstance().buildPartNumber();

        System.out.println(a.length());
        System.out.println(b);
    }
}
