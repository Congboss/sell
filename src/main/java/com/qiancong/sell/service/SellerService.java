package com.qiancong.sell.service;

import com.qiancong.sell.entity.SellerInfo;

public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
