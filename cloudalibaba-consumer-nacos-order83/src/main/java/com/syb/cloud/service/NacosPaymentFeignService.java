package com.syb.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @author syb
 */
@Component
@FeignClient(value = "nacos-payment-provider")
public interface NacosPaymentFeignService {

    /**
     * open feign 获取
     * @author YuanBo.Shi
     * @date 2021/9/7 10:13 上午
     * @param id id
     * @return com.syb.cloud.entities.ResultVo
     */
    @GetMapping(value = "/payment/nacos/{id}")
    String getPaymentById(@PathVariable("id") Long id);

}
