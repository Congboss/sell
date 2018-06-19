package com.qiancong.sell.utils;

import com.qiancong.sell.VO.ResultVO;
import lombok.Data;

/**
 * @program: sell
 * @description:
 * @author: Cong.Qian
 * @create: 2018-06-13 10:25
 **/

public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        return resultVO;
    }
    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error (Integer code,String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setMessage(msg);
        resultVO.setCode(code);
        return resultVO;
    }
}
