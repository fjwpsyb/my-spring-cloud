package com.syb.cloud.config;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YuanBo.Shi
 * @date 2021年09月13日 6:48 下午
 */
@Configuration
public class FilterContextConfig {

    @Bean
    public FilterRegistrationBean sentinelFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CommonFilter());
        registrationBean.addUrlPatterns("/*");
        // 入口资源关闭聚合
        registrationBean.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY, "false");
        registrationBean.setName("sentinelFilter");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}