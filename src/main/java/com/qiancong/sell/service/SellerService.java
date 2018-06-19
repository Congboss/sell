package com.qiancong.sell.service;

import com.qiancong.sell.dataobject.SellerInfo;

public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
