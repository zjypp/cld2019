package com.zjy.cld2019.orderservice.dao;

import com.zjy.cld2019.orderservice.model.GoodsSku;
import com.zjy.cld2019.orderservice.model.GoodstOrder;
import org.apache.ibatis.annotations.*;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/1 9:13 AM
 * @Version: 1.0
 * @Des：
 */
@Mapper
public interface OrderMapper {

    @Select("SELECT * from goods_sku where sku_id=#{skuId} limit 1 ")
    GoodsSku getGoodsSku(String skuId);

    @Update("update goods_sku set available_stock = available_stock - #{amount} ,freeze_stock = freeze_stock + #{amount} where sku_id= #{skuId} and available_stock >= #{amount}")
    int decreaseGoodSku(@Param("skuId") String skuId, @Param("amount") int amount);

    @Insert("insert goods_order(user_id,sku_id,amount,price) values(#{userId},#{skuId},#{amount},#{price}) ")
    int addOrder(GoodstOrder goodstOrder);


    @Update("update goods_sku set freeze_stock = freeze_stock - #{amount} where sku_id= #{skuId} and freeze_stock >= #{amount} ")
    int decreaseGoodSkuConfirm(@Param("skuId") String skuId, @Param("amount") int amount);

    @Update("update goods_sku set available_stock = available_stock + #{amount}, freeze_stock = freeze_stock + #{amount} where sku_id= #{skuId} ")
    int decreaseGoodSkuCancel(@Param("skuId") String skuId, @Param("amount") int amount);
}
