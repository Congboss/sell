package com.qiancong.sell.service.impl;

import com.qiancong.sell.mapper.SellerInfoMapper;
import com.qiancong.sell.entity.SellerInfo;
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
    SellerInfoMapper mapper;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return mapper.findSellerInfoByOpenid(openid);
    }

    @Override
    public String getPasswordByUserName(String username) {
        return mapper.getPasswordByuserName(username);
    }
}
