package com.qiancong.sell.dao;

import com.qiancong.sell.entity.ProductInfo;
import com.qiancong.sell.mapper.ProductInfoRepository;
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
public class PorductInfoRepositoryTest {
@Autowired
private ProductInfoRepository productInfoRepository;
    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList=productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());

    }

    @Test
    public void saveTest(){
ProductInfo productInfo=new ProductInfo();
productInfo.setProductId("12389");
productInfo.setProductName("皮蛋0");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝0");
        productInfo.setProductIcon("http:xxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);

productInfoRepository.save(productInfo);

    }

    @Test
    public void findOneTest(){

    }

    @Test
    public void findAllTest(){

    }
}