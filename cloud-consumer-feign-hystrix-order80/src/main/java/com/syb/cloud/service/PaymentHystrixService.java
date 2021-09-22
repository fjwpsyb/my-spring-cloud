package com.syb.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author syb
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallbackServiceImpl.class)
public interface PaymentHystrixService {

    /**
     * feign paymentInfoSuccess
     * @author YuanBo.Shi
     * @date 2021/9/7 4:33 下午
     * @param id id
     * @return java.lang.String
     */
    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfoSuccess(@PathVariable("id") Integer id);

    /**
     * feign paymentInfoTimeOut
     * @author YuanBo.Shi
     * @date 2021/9/7 4:33 下午
     * @param id id
     * @return java.lang.String
     */
    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfoTimeOut(@PathVariable("id") Integer id);
}
