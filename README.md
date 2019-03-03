# cld2019
搭建一个面向业务通用的微服务框架，目前是使用F.SR2最新版本

1.consul
服务注册发现使用consul










业务描述
定义了4个业务服务，  
1.用户服务（登陆、注册、查询用户），  
2.支付服务（开通支付账户、查询账户资金、支付，充值，提现;）  
3.营销服务（发券，使用券、查询某用户下券）  
4.订单服务（下订单）  
  
备注附录  
1.Druid的加密参考https://github.com/alibaba/druid/wiki/使用ConfigFilter  
2.zipkin下载启动参考https://zipkin.io/pages/quickstart.html