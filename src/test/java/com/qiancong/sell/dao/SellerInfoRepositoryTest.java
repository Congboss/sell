package com.qiancong.sell.dao;

import com.qiancong.sell.dataobject.SellerInfo;
import com.qiancong.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {
@Autowired
private SellerInfoRepository repository;
    @Test
    public void findByOpenid() {
SellerInfo sellerInfo=repository.findByOpenid("abc");
Assert.assertEquals("abc",sellerInfo.getOpenid());
    }
    @Test
    public void save(){
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setOpenid("abc");
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
       SellerInfo result= repository.save(sellerInfo);
        Assert.assertNotEquals(null,result);
    }
}