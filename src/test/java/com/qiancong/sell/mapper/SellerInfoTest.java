package com.qiancong.sell.mapper;

import com.qiancong.sell.entity.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoTest {
@Autowired
private SellerInfoMapper mapper;
    @Test
    public void insertSeller() {
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setSellerId("22");
        sellerInfo.setUsername("werr");
        sellerInfo.setPassword("ser");
        sellerInfo.setOpenid("we");
        mapper.insertSeller(sellerInfo);
    }
    @Test
    public void findSellerByOpenId(){
        log.info(mapper.findSellerInfoByOpenid("we").getPassword());
    }
}