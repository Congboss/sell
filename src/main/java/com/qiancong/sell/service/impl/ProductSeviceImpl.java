package com.qiancong.sell.service.impl;

import com.qiancong.sell.dao.ProductInfoRepository;
import com.qiancong.sell.dataobject.ProductInfo;
import com.qiancong.sell.dto.CartDTO;
import com.qiancong.sell.enums.ProductStatusEnum;
import com.qiancong.sell.enums.ResultEnum;
import com.qiancong.sell.exception.SellException;
import com.qiancong.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
    /**
    * @Description:
    * @Param:
    * @return:
    * @Author: Cong.Qian
    * @Date: 2018/6/13
    */
@Service
public class ProductSeviceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
for(CartDTO cartDTO:cartDTOList){
    ProductInfo productInfo=repository.findById(cartDTO.getProductId()).get();
    if(productInfo==null){
        throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
    }
    Integer result=productInfo.getProductStock()+cartDTO.getProductQuantity();
    productInfo.setProductStock(result);

    repository.save(productInfo);
}
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
for(CartDTO cartDTO:cartDTOList){
    ProductInfo productInfo=repository.findById(cartDTO.getProductId()).get();
    if(productInfo==null){
        throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
    }
    Integer result=productInfo.getProductStock()-cartDTO.getProductQuantity();
    if(result<0){
        throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
    }
  productInfo.setProductStock(result);
    repository.save(productInfo);
}
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }
}
