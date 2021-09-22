package com.syb.cloud.service;

import com.syb.cloud.entities.Payment;
import com.syb.cloud.entities.ResultVo;
import com.syb.cloud.entities.ResultVoUtil;
import org.springframework.stereotype.Component;

/**
 * @author syb
 */
@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public ResultVo paymentSQL(Long id) {
        return ResultVoUtil.error(44444, "服务降级返回,---PaymentFallbackService", new Payment(id, "errorSerial"));
    }
}
