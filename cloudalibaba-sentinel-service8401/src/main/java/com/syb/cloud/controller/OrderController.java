package com.syb.cloud.controller;

import com.syb.cloud.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试流控规则  --》 链路   同一上级接口挂后，同步报错
 * @author YuanBo.Shi
 * @date 2021年09月13日 6:47 下午
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @RequestMapping("/order/message1")
    public String message1() {
        this.orderServiceImpl.dosomething();
        return "message1";
    }

    @RequestMapping("/order/message2")
    public String message2() {
        this.orderServiceImpl.dosomething();
        return "message2";
    }

}
