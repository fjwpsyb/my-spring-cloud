package com.syb.cloud.controller;

import com.syb.cloud.service.NacosPaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author syb
 */
@RestController
@Slf4j
public class OrderNacosController {

    @Resource
    private NacosPaymentFeignService paymentFeignService;


    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

}
