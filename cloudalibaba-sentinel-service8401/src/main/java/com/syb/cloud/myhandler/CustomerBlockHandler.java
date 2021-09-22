package com.syb.cloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.syb.cloud.entities.CommonResult;
import com.syb.cloud.entities.ResultVo;
import com.syb.cloud.entities.ResultVoUtil;

/**
 * 自定义降级方法类
 * @author syb
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444, "按客戶自定义,global handlerException----1");
    }

    public static ResultVo handlerException2(BlockException exception) {
        return ResultVoUtil.error(4444, "按客戶自定义,global handlerException----2");
    }
}
