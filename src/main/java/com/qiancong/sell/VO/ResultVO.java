package com.qiancong.sell.VO;

import lombok.Data;

@Data
/*http请求返回的
        最外层对象*/
public class ResultVO<T> {
    //错误码
    private Integer code;
    //提示信息
    private String message;
    //返回的具体内容
    private T data;
}
