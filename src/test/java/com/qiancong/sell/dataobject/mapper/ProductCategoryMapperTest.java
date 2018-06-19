package com.qiancong.sell.dataobject.mapper;

import com.qiancong.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {
@Autowired
private ProductCategoryMapper mapper;
    @Test
    public void insertByMap() {
        Map<String,Object> map=new HashMap<>();
        map.put("category_name","聪聪最爱");
        map.put("category_type",199);
        int result=mapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }
    @Test
    public void insertByObject(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("cc最爱");
        productCategory.setCategoryType(102);
        int result=mapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }
    @Test
    public void findByCategoryType(){
        ProductCategory result=mapper.findByCategoryType(102);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateCategoryType(){
     int result=  mapper.updateCategoryType("陈宝宝",2);
     Assert.assertEquals(1,result);
    }

    @Test
    public void updateByObject(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("cc最不爱");
        productCategory.setCategoryType(2);
        int result=mapper.updateByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteByCategoeyType(){
        int result=mapper.deleteByCategoryType(2);
        Assert.assertEquals(1,result);
    }
}