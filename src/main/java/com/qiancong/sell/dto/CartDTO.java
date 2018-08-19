package com.qiancong.sell.dto;

import lombok.Getter;

/**
 * @program: sell
 * @description: 购物车
 * @author: Cong.Qian
 * @create: 2018-06-13 17:53
 **/
@Getter
public class CartDTO {
    //商品Id
    private String productId;
//数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
