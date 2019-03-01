package com.zjy.cld2019.payservice.dao;

import com.zjy.cld2019.payservice.model.PaymentRecord;
import com.zjy.cld2019.payservice.model.UserAccount;
import org.apache.ibatis.annotations.*;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/28 7:24 PM
 * @Version: 1.0
 * @Des：
 */
@Mapper
public interface PayMapper {

    @Insert("insert user_account(user_id,pay_password) values(#{userId},#{payPassword})")
    int openUserAccount(@Param("userId") String userId,@Param("payPassword") String payPassword);

    @Select("SELECT * from user_account  where user_id=#{userId} limit 1")
    UserAccount getUserAccount(String userId);

    @Select("select * from user_account where user_id=#{userId} and pay_password=#{payPassword};")
    UserAccount getUserAccountByPayPassword(@Param("userId") String userId, @Param("payPassword") String payPassword);


    @Insert("INSERT payment_record(user_id,amount,type,status) values(#{userId},#{amount},#{type},#{status})")
    int addPaymentRecord(PaymentRecord paymentRecord);


    @Update("update user_account set available_amount = available_amount + #{amount} where user_id=#{userId} ")
    int increaseUserAccount(@Param("userId") String userId,@Param("amount") double amount);

    @Update("update user_account set available_amount = available_amount - #{amount} where user_id=#{userId} ")
    int decreaseUserAccount(@Param("userId") String userId,@Param("amount") double amount);


}
