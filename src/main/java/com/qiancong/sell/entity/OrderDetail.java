package com.qiancong.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: sell
 * @description: 订单详情表
 * @author: Cong.Qian
 * @create: 2018-06-13 12:38
 **/
@Entity
@Data
@DynamicUpdate
public class OrderDetail {
    //详单id
    @Id
    private String detailId;
    //订单id
    private String orderId;
    //商品id
    private String productId;
    //商品名称
    private String productName;
    //商品价格
    private BigDecimal productPrice;
    //商品库存
    private Integer productQuantity;
    //商品小图
    private String productIcon;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}
