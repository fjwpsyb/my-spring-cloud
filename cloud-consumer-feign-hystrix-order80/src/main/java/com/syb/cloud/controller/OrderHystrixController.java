package com.syb.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.syb.cloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * DefaultProperties hystrix 全局降级方法设置属性
 * @author syb
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoSuccess(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfoSuccess(id);
    }

    /**
     * 如果客户端超过限定的时间，则走降级方法
     * HystrixCommand 可以指定具体降级方法，也可以使用默认全局global
     * @author YuanBo.Shi
     * @date 2021/9/7 8:46 下午
     * @param id id
     * @return java.lang.String
     */
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            // 超过限定值则走降级的方法
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000")
    })
    // @HystrixCommand
    public String paymentInfoTimeOut(@PathVariable("id") Integer id) {
        int age = 10 / 0;
        return paymentHystrixService.paymentInfoTimeOut(id);
    }

    /**
     * 局部降级的方法
     * @author YuanBo.Shi
     * @date 2021/9/8 9:09 上午
     * @param id id
     * @return java.lang.String
     */
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    /**
     * 全局fallback方法
     * @author YuanBo.Shi
     * @date 2021/9/7 9:21 下午
     * @return java.lang.String
     */
    public String paymentGlobalFallbackMethod() {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
