package com.syb.cloud.entities;

import lombok.Data;

/**
 * @author YuanBo.shi
 * @date Created in 2020/10/29 8:48 下午
 */
@Data
public class ResultVo {

    private Integer code;

    private String message;

    private Object data;
}