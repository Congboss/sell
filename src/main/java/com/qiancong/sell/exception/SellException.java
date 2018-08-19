package com.qiancong.sell.exception;

import com.qiancong.sell.enums.ResultEnum;
import lombok.Data;

/**
 * @program: sell
 * @description:
 * @author: Cong.Qian
 * @create: 2018-06-13 17:05
 **/
@Data
public class SellException extends RuntimeException{
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code=resultEnum.getCode();

    }
    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
