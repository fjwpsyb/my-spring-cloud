package com.syb.cloud.config;

import org.springframework.web.client.RestTemplate;

/**
 * @author syb
 */
// @Configuration
public class ApplicationContextConfig {
    // @Bean
    // @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
