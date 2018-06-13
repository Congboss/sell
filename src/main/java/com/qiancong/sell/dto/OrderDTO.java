package com.qiancong.sell.dto;

import com.qiancong.sell.dataobject.OrderDetail;
import com.qiancong.sell.enums.OrderStatusEnum;
import com.qiancong.sell.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: sell
 * @description:
 * @author: Cong.Qian
 * @create: 2018-06-13 16:30
 **/
@Data
public class OrderDTO {

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
    private Integer orderStatus;
    //支付状态,默认为0，未支付
    private Integer payStatus;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
