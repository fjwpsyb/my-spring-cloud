package com.syb.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.syb.cloud.entities.Payment;
import com.syb.cloud.entities.ResultVo;
import com.syb.cloud.entities.ResultVoUtil;
import com.syb.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author syb
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    /**
     * fallback 运行异常捕获
     * blockHandler sentinel配置异常捕获
     * exceptionsToIgnore 异常忽略（兜底方法不管忽略的异常）
     * @author YuanBo.Shi
     * @date 2021/9/15 8:07 下午
     * @param id id
     * @return com.syb.cloud.entities.ResultVo
     */
    @RequestMapping("/consumer/fallback/{id}")
    @SentinelResource(
            value = "fallback",
            fallback = "handlerFallback",
            blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public ResultVo fallback(@PathVariable Long id) {
        ResultVo vo = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, ResultVo.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (vo.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return vo;
    }

    /**
     * 本例是fallback
     * @param id id
     * @param e e
     * @return ResultVo
     */
    public ResultVo handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return ResultVoUtil.error(444, "兜底异常handlerFallback,exception内容  " + e.getMessage(), payment);
    }

    /**
     * 本例是blockHandler
     * @author YuanBo.Shi
     * @date 2021/9/14 8:40 下午
     * @param id id
     * @param blockException  blockException
     * @return com.syb.cloud.entities.CommonResult
     */
    public ResultVo blockHandler(@PathVariable Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return ResultVoUtil.error(445, "blockHandler-sentinel限流,无此流水: blockException  " + blockException.getMessage()
                , payment);
    }

    //==================OpenFeign

    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public ResultVo payment(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }
}
