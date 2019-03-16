package com.zjy.cld2019.orderservice.service;

import com.zjy.cld2019.orderservice.dao.OrderMapper;
import com.zjy.cld2019.orderservice.model.GoodsSku;
import com.zjy.cld2019.orderservice.model.GoodstOrder;
//import org.dromara.hmily.annotation.Hmily;
//import org.dromara.hmily.common.exception.HmilyRuntimeException;


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

    //Logger logger = LoggerFactory.getLogger(this.getClass());

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

    /**
     * 模拟正常分布式请求
     * @param skuId
     * @param amount
     * @return
     */
   // @Hmily(confirmMethod = "skuConfirmMethod", cancelMethod = "skuCancelMethod")
    public  boolean decreaseSkuTry(String skuId,int amount){
        int result = orderMapper.decreaseGoodSku(skuId,amount);
        return result == 1?true:false;
    }

    /**
     * 模拟异常情况下的分布式请求
     * @param skuId
     * @param amount
     * @return
     */
    //@Hmily(confirmMethod = "skuConfirmMethod", cancelMethod = "skuCancelMethod")
    public boolean decreaseSkuMockWithExceptionTry(String skuId,int amount){
        //int result = orderMapper.decreaseGoodSku(skuId,amount);
        //throw new HmilyRuntimeException("库存扣减异常！");
        return false;
    }
    /**
     * 模拟超时情况下的分布式请求
     * @param skuId
     * @param amount
     * @return
     */
   // @Hmily(confirmMethod = "skuConfirmMethod", cancelMethod = "skuCancelMethod")
    public boolean decreaseSkuMockWithTimeoutTry(String skuId,int amount){
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result = orderMapper.decreaseGoodSku(skuId,amount);
        return result == 1?true:false;
    }

    public  boolean skuConfirmMethod(String skuId,int amount){
        int result = orderMapper.decreaseGoodSkuConfirm(skuId,amount);
        return result == 1?true:false;
    }
    public  boolean skuCancelMethod(String skuId,int amount){
        int result = orderMapper.decreaseGoodSkuCancel(skuId,amount);
        return result == 1?true:false;
    }
}
