package com.qiancong.sell.utils;

import com.qiancong.sell.enums.CodeEnum;

/**
 * @program: sell
 * @description:
 * @author: Cong.Qian
 * @create: 2018-06-16 18:05
 **/
public class EnumUtil<T> {
    public static<T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
for(T each: enumClass.getEnumConstants()){
    if(code.equals(each.getCode())){
        return each;
    }
}
return null;
    }
}
