package com.qiancong.sell.entity;

import com.qiancong.sell.enums.OrderStatusEnum;
import com.qiancong.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: sell
 * @description: 订单主表
 * @author: Cong.Qian
 * @create: 2018-06-13 12:16
 **/
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
    //订单id
    private String orderId;
//买家姓名
    private String buyerName;
//买家电话
    private String buyerPhone;
//买家住址
    private String buyerAddress;
//买家微信Openid
    private String buyerOpenid;
//订单总金额
    private BigDecimal orderAmount;
//订单状态，默认为0,新下单
    private Integer orderStatus=OrderStatusEnum.NEW.getCode();
//支付状态,默认为0，未支付
    private Integer payStatus=PayStatusEnum.WAIT.getCode();
//创建时间
private Date createTime;
//更新时间
    private Date updateTime;


}
