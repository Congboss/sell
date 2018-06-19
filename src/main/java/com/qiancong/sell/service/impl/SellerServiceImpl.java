package com.qiancong.sell.service.impl;

import com.qiancong.sell.dao.SellerInfoRepository;
import com.qiancong.sell.dataobject.SellerInfo;
import com.qiancong.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: sell
 * @description:
 * @author: Cong.Qian
 * @create: 2018-06-18 15:19
 **/
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerInfoRepository repository;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
