package com.syb.cloud.controller;

import com.syb.cloud.entities.Payment;
import com.syb.cloud.entities.ResultVo;
import com.syb.cloud.entities.ResultVoUtil;
import com.syb.cloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author syb
 */
@Slf4j
@RestController
public class OrderController {

    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer myLoadBalanced;


    /**
     * 创建订单 postForObject
     *
     * @param payment payment
     * @return ResultVo
     * @author YuanBo.Shi
     * @date 2021/9/1 12:29 下午
     */
    @GetMapping("/consumer/payment/create")
    public ResultVo create(Payment payment) {
        return ResultVoUtil.success(restTemplate.postForObject(PAYMENT_URL + "/payment/create",
                payment, ResultVo.class));
    }

    /**
     * 创建订单 postForEntity
     *
     * @param payment payment
     * @return ResultVo
     * @author YuanBo.Shi
     * @date 2021/9/1 12:29 下午
     */
    @GetMapping("/consumer/payment1/create")
    public ResultVo create1(Payment payment) {
        ResponseEntity<ResultVo> response = restTemplate.postForEntity(PAYMENT_URL + "/payment/create",
                payment, ResultVo.class);
        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getStatusCodeValue());
        return ResultVoUtil.success(response.getBody());
    }

    /**
     * 消费者查询订单 getForObject
     *
     * @param id id
     * @return ResultVo
     * @author YuanBo.Shi
     * @date 2021/9/1 12:33 下午
     */
    @GetMapping("/consumer/payment/get/{id}")
    public ResultVo getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, ResultVo.class);
    }

    /**
     * 消费者查询订单 getForEntity
     *
     * @param id id
     * @return ResultVo
     * @author YuanBo.Shi
     * @date 2021/9/1 12:33 下午
     */
    @GetMapping("/consumer/payment1/get/{id}")
    public ResultVo getPayment1(@PathVariable("id") Long id) {
        ResponseEntity<ResultVo> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id,
                ResultVo.class);
        System.out.println(entity.getBody());
        System.out.println(entity.getHeaders());
        System.out.println(entity.getStatusCodeValue());
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }
        return ResultVoUtil.error(444, "操作失败");

    }

    /**
     * 自定义loadBalancer
     *
     * @return java.lang.String
     * @author YuanBo.Shi
     * @date 2021/9/6 7:40 下午
     */
    @GetMapping(value = "consumer/payment/lb")
    public String paymentLoaderBalancer() {
        // 获取实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances.isEmpty()) {
            return null;
        }
        ServiceInstance instance = myLoadBalanced.instances(instances);
        return restTemplate.getForObject(instance.getUri() + "/payment/lb", String.class);
    }


    /**
     * zipkin+sleuth
     *
     * @return java.lang.String
     * @author YuanBo.Shi
     * @date 2021/9/11 2:58 下午
     */
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin() {
        return restTemplate.getForObject("http://localhost:8001" + "/payment/zipkin/", String.class);
    }
}
