package com.qiancong.sell.mapper;

import com.qiancong.sell.entity.SellerInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface SellerInfoMapper {
    @Insert("insert into sellerInfo(seller_id,user_name,password,open_id) values(#{sellerId},#{username},#{password},#{openid})")
    public void insertSeller(SellerInfo sellerInfo);
    @Select("select * from sellerInfo where open_id=#{openId}")
    public SellerInfo findSellerInfoByOpenid(String openId);
    @Select("select password from sellerInfo where user_name=#{username}")
    public String getPasswordByuserName(String username);
}
