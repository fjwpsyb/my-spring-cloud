package com.syb.cloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author syb
 */
@Configuration
@MapperScan({"com.syb.cloud.dao"})
public class MyBatisConfig {
}
