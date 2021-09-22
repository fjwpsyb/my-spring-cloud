package com.syb.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author syb
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t" + "...testB");
        return "------testB";
    }


    /**
     * 测试降级规则 rt /  异常比例
     * @author YuanBo.Shi
     * @date 2021/9/14 3:30 下午
     * @return java.lang.String
     */
    @GetMapping("/testD")
    public String testD() {
        // 案例一 rt
       // try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
       // log.info("testD 测试RT" + Thread.currentThread().getName());


        // 案例二 异常比例
        log.info("testD 异常比例");
        int age = 10 / 0;
        return "------testD";
    }

    /**
     * 测试降级规则  异常数
     * @author YuanBo.Shi
     * @date 2021/9/14 3:30 下午
     * @return java.lang.String
     */
    @GetMapping("/testE")
    public String testE() {
        log.info("testE 测试异常数");
        int age = 10 / 0;
        return "------testE 测试异常数";
    }

    /**
     * 测试热点限流规则 参数角标 超过限流的阀值则进入降级的方法
     * SentinelResource 只负责配置规则兜底，程序runtimeException错误不兜底
     * @author YuanBo.Shi
     * @date 2021/9/14 3:46 下午
     * @param p1 p1
     * @param p2 p2
     * @return java.lang.String
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "dealTestHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        //int age = 10/0;
        return "------testHotKey";
    }

    /**
     * 降级兜底的方法
     * @author YuanBo.Shi
     * @date 2021/9/14 3:58 下午
     * @param p1 p1
     * @param p2 p2
     * @param exception  exception
     * @return java.lang.String
     */
    public String dealTestHotKey(String p1, String p2, BlockException exception) {
        // sentinel系统默认的提示：Blocked by Sentinel (flow limiting)
        return "------dealTestHotKey,o(╥﹏╥)o";
    }

}
