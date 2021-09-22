package com.syb.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author YuanBo.Shi
 * @date 2021年09月13日 6:47 下午
 */
@Service
public class OrderServiceImpl {

    @SentinelResource("dosomething")       // 将此方法标注为sentinel的资源。value=资源名
    public void dosomething() {
        System.out.println("do something");
    }
}
