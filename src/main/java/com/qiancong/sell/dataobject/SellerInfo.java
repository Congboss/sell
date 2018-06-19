package com.qiancong.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @program: sell
 * @description:卖家信息表
 * @author: Cong.Qian
 * @create: 2018-06-18 15:06
 **/
@Entity
@Data
public class SellerInfo {
    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
