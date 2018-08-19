package com.qiancong.sell.utils;

import java.util.Random;

/**
 * @program: sell
 * @description:生成随机数，用于传入orderId和DetailId
 * @author: Cong.Qian
 * @create: 2018-06-13 17:41
 **/
public class KeyUtil {
        /**
        * @Description:  生成唯一的主键
        * @Param:
        * @return:
        * @Author: Cong.Qian
        * @Date: 2018/6/13
        */
    public static synchronized String genUniqueKey(){
        Random random=new Random();
       ;
       Integer number= random.nextInt(900000)+100000;

       return  System.currentTimeMillis()+String.valueOf(number);
    }
}
