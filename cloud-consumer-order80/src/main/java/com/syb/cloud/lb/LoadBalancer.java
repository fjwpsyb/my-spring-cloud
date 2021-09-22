package com.syb.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;


/**
 * @author syb
 */
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
