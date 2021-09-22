package com.syb.cloud.entities;


/**
 * @author YuanBo.shi
 * @date Created in 2020/10/29 8:52 下午
 */
public class ResultVoUtil {

    public static ResultVo success(Object object) {
        ResultVo resultVO = new ResultVo();
        resultVO.setCode(200);
        resultVO.setMessage("请求成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo success(Object object, String message) {
        ResultVo resultVO = new ResultVo();
        resultVO.setCode(200);
        resultVO.setMessage(message);
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVo error(Integer code, String msg) {
        ResultVo resultVO = new ResultVo();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        return resultVO;
    }

    public static ResultVo error(Integer code, String msg, Object object) {
        ResultVo resultVO = new ResultVo();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        resultVO.setData(object);
        return resultVO;
    }

}
