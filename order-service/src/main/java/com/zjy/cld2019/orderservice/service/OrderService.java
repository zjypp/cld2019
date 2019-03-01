package com.zjy.cld2019.orderservice.service;

import com.zjy.cld2019.orderservice.dao.OrderMapper;
import com.zjy.cld2019.orderservice.model.GoodsSku;
import com.zjy.cld2019.orderservice.model.GoodstOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/1 10:09 AM
 * @Version: 1.0
 * @Des：
 */
@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    public GoodsSku getGoodsSku(String skuId){
        return orderMapper.getGoodsSku(skuId);
    }

    public boolean decreaseGoodsSku(String skuId,int amount){
        int result = orderMapper.decreaseGoodSku(skuId,amount);
        return result == 1?true:false;
    }

    /**
     *下订单
     * @param goodstOrder
     * @return -1库存不足，1成功，0失败；
     */
    public int addOrder(GoodstOrder goodstOrder){
        GoodsSku goodsSku = getGoodsSku(goodstOrder.getSkuId());
        //有效库存的情况才可以下订单
        if(goodsSku !=null && goodsSku.getAvailableStock() >= goodstOrder.getAmount()){

            decreaseGoodsSku(goodstOrder.getSkuId(),goodstOrder.getAmount());//减库存
            int result = orderMapper.addOrder(goodstOrder);//增加订单记录
            return  result;
        }else{
            return -1;//库存不存在或者库存不足
        }

    }
}
