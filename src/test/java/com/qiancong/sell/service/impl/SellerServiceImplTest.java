package com.qiancong.sell.service.impl;

import com.qiancong.sell.dataobject.SellerInfo;
import com.qiancong.sell.service.SellerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {
    private static final String openid="abc";
@Autowired
    private SellerService sellerService;
    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo result= sellerService.findSellerInfoByOpenid(openid);
        Assert.assertEquals(openid,result.getOpenid());
    }
}