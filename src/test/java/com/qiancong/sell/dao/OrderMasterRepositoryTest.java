package com.qiancong.sell.dao;

import com.qiancong.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
@Autowired
private OrderMasterRepository repository;
private final String OPENID="110";
    @Test
    public void findByBuyerOpenid() {
        PageRequest request=new PageRequest(0,3);
        Page<OrderMaster> result=repository.findByBuyerOpenid(OPENID,request);

        Assert.assertNotEquals(0,result.getTotalElements());
    }
    @Test
    public void saveTest(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setBuyerAddress("南京邮电大学");
        orderMaster.setBuyerName("Congboss");
        orderMaster.setBuyerPhone("15850677993");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderId("123457");
        OrderMaster result=repository.save(orderMaster);
        Assert.assertNotEquals(null,result);
    }
}