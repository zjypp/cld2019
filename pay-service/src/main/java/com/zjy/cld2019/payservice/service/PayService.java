package com.zjy.cld2019.payservice.service;

import com.zjy.cld2019.common.utils.PwdUtil;
import com.zjy.cld2019.common.utils.StringUtil;
import com.zjy.cld2019.payservice.dao.PayMapper;
import com.zjy.cld2019.payservice.model.PaymentRecord;
import com.zjy.cld2019.payservice.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/28 7:11 PM
 * @Version: 1.0
 * @Des：
 */
@Service
public class PayService {

    @Autowired
    PayMapper payMapper;

    /**
     * 开通支付账户
     * @param userId
     * @param payPassword
     * @return
     */
    public boolean openUserAccount(String userId,String payPassword){
        if(StringUtil.isEmpty(payPassword) || payPassword.length()<6){
            return false;
        }else{
            payPassword = PwdUtil.generatePayPwd(payPassword);

            if(payMapper.getUserAccount(userId)==null){
                int count = payMapper.openUserAccount(userId,payPassword);
                return count ==1 ?true:false;
            }else{
                return false;
            }


        }
    }

    public UserAccount getUserAccount(String userId){
        UserAccount userAccount = payMapper.getUserAccount(userId);
        return userAccount;
    }

    /**
     * 充值
     * @param userId
     * @param amount
     * @return
     */
    public boolean recharge(String userId,double amount){

        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setUserId(userId);
        paymentRecord.setAmount(amount);
        paymentRecord.setType(1);
        payMapper.addPaymentRecord(paymentRecord);
        int count = payMapper.increaseUserAccount(userId,amount);
        return  count == 1?true:false;
    }

    /**
     *
     * 提现
     * @param userId
     * @param amount
     * @return
     */
    public boolean withdraw(String userId,double amount){
        UserAccount userAccount = payMapper.getUserAccount(userId);
        if(userAccount == null){
            return false;
        }

        if(userAccount.getAvailableAmount()>=amount){
            int count = payMapper.decreaseUserAccount(userId,amount);
            return  count==1?true:false;
        }else{
            return false;
        }

    }

    /**
     * 支付（下订单前的验证，判断支付密码，和账户资金是否足够）
     * @param userId
     * @param price 使用的资金
     * @param payPassword
     * @return -1账户或者密码错误，1成功，0失败
     */
    public int useMoney(String userId,double price,String payPassword){
        payPassword = PwdUtil.generatePayPwd(payPassword);
        UserAccount userAccount = payMapper.getUserAccountByPayPassword(userId,payPassword);
        if(userAccount == null){
            return -1;
        }

        if(userAccount.getAvailableAmount()>=price){
            int count = payMapper.decreaseUserAccount(userId,price);
            return count;
        }else{
          return 0;
        }
    }
}
