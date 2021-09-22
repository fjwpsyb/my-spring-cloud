package com.syb.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 负载均衡算法：rest接口第几次请求 % 服务器集群总数量 = 实际调用服务器位置下标（每次服务重启，重新从1开始计数）
 * @author YuanBo.Shi
 * @date 2021年09月01日 12:25 下午
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    // 如果使用自定义负载算法，则注释此处
    // @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
