package com.qiancong.sell.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    UP(1,"下架"),
    DOWN(0,"在架")
    ;

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
