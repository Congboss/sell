package com.qiancong.sell.service;

import com.qiancong.sell.entity.ProductInfo;
import com.qiancong.sell.dto.CartDTO;
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
void increaseStock(List<CartDTO> cartDTOList);
    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
 ProductInfo onSale(String productId);
    //下架
    ProductInfo offSale(String productId);

}
