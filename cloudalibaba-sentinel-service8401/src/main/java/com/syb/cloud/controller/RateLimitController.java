package com.syb.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.syb.cloud.entities.CommonResult;
import com.syb.cloud.entities.Payment;
import com.syb.cloud.entities.ResultVo;
import com.syb.cloud.entities.ResultVoUtil;
import com.syb.cloud.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
    }

    /**
     * 流控规则 兜底方法
     * @author YuanBo.Shi
     * @date 2021/9/14 6:48 下午
     * @param exception exception
     * @return com.syb.cloud.entities.CommonResult
     */
    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }

    /**
     * 根据url 流控限制
     * @author YuanBo.Shi
     * @date 2021/9/14 7:58 下午
     * @return com.syb.cloud.entities.CommonResult
     */
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "按url限流测试OK", new Payment(2020L, "serial002"));
    }


    /**
     * 指定兜底方法
     * @author YuanBo.Shi
     * @date 2021/9/14 7:57 下午
     * @return com.syb.cloud.entities.ResultVo
     */
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public ResultVo customerBlockHandler() {
        return ResultVoUtil.success(new Payment(2020L, "serial003"), "按客戶自定义");
    }
}