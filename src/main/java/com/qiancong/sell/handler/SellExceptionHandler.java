package com.qiancong.sell.handler;

import com.qiancong.sell.VO.ResultVO;
import com.qiancong.sell.exception.SellException;
import com.qiancong.sell.utils.ResultVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: sell
 * @description:
 * @author: Cong.Qian
 * @create: 2018-06-18 17:56
 **/
@ControllerAdvice
public class SellExceptionHandler {
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());

    }

}
