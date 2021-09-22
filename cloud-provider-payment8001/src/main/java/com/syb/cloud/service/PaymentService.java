package com.syb.cloud.service;

import com.syb.cloud.entities.Payment;
import org.apache.ibatis.annotations.Param;


/**
 * @author syb
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
