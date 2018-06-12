package com.qiancong.sell.service;

import com.qiancong.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    ProductInfo findOne(String productId);
    //查询上线商品
    List<ProductInfo> findUpAll();
//查询所有商品
Page<ProductInfo> findAll(Pageable pageable);

ProductInfo save(ProductInfo productInfo);

//加库存

    //减库存
}