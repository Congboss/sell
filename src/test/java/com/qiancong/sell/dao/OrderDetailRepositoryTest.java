package com.qiancong.sell.dao;

import com.qiancong.sell.entity.OrderDetail;
import com.qiancong.sell.mapper.OrderDetailRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
@Autowired
private OrderDetailRepository repository;
    @Test
    public void findByOrderId() {
      List<OrderDetail> orderDetailList= repository.findByOrderId("123456");
      Assert.assertNotEquals(0,orderDetailList.size());
    }
    @Test
    public void saveTest(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("123456789");
        orderDetail.setOrderId("123456");
        orderDetail.setProductIcon("http:xxxx.jpg");
        orderDetail.setProductId("11111112");
        orderDetail.setProductPrice(new BigDecimal(3.2));
        orderDetail.setProductName("旺旺雪饼");
        orderDetail.setProductQuantity(100);
       OrderDetail result=repository.save(orderDetail);
        Assert.assertNotEquals(null,result);
    }
}