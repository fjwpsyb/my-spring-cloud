package com.syb.cloud.service;

import org.springframework.stereotype.Component;


/**
 * 统一服务降级类 需要配合yml中 feign.hystrix.enabled=true
 * 如果开启了上诉配置，则每次都进入兜底方法paymentTimeOutFallbackMethod
 *
 * @author syb
 */
@Component
public class PaymentFallbackServiceImpl implements PaymentHystrixService {

    @Override
    public String paymentInfoSuccess(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }
}
