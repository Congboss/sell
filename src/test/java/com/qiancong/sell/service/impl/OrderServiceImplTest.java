package com.qiancong.sell.service.impl;

import com.qiancong.sell.dataobject.OrderDetail;
import com.qiancong.sell.dto.OrderDTO;
import com.qiancong.sell.enums.OrderStatusEnum;
import com.qiancong.sell.enums.PayStatusEnum;
import com.qiancong.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
    /**
    * @Description:
    * @Param:
    * @return:
    * @Author: Cong.Qian
    * @Date: 2018/6/13
    */
public class OrderServiceImplTest {
@Autowired
    OrderService orderService;
private final String BUYER_OPENID="1101110";
private final String ORDER_ID="1528889833056934274";
    @Test
    public void create() {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("CongQian");
        orderDTO.setBuyerAddress("南京邮电大学");
        orderDTO.setBuyerPhone("12345667");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList=new ArrayList<>();

        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setProductId("123457");
        orderDetail.setProductQuantity(9);
        orderDetailList.add(orderDetail);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result=orderService.create(orderDTO);
        log.info("创建订单 result={}",result);
        Assert.assertNotNull(result);


    }

    @Test
    public void findOne() {
        OrderDTO result=orderService.findOne(ORDER_ID);
        log.info("查询单个订单 result={}",result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request=new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage=orderService.findList(BUYER_OPENID,request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO=orderService.findOne(ORDER_ID);
        OrderDTO result=orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO=orderService.findOne(ORDER_ID);
        OrderDTO result=orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
OrderDTO orderDTO=orderService.findOne(ORDER_ID);
OrderDTO result=orderService.paid(orderDTO);
Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());

    }
    @Test
    public void findlist(){
        PageRequest request=new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage=orderService.findList(request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }
}