package com.syb.cloud.service;

import com.syb.cloud.entities.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @author syb
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    /**
     * open feign 获取
     * @author YuanBo.Shi
     * @date 2021/9/7 10:13 上午
     * @param id id
     * @return com.syb.cloud.entities.ResultVo
     */
    @GetMapping(value = "/payment/get/{id}")
    ResultVo getPaymentById(@PathVariable("id") Long id);

    /**
     * timeout
     * @author YuanBo.Shi
     * @date 2021/9/7 10:13 上午
     * @return java.lang.String
     */
    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}
