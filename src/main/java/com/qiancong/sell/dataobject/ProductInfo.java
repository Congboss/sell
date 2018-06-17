package com.qiancong.sell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qiancong.sell.enums.ProductStatusEnum;
import com.qiancong.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo {
    @Id
    private String productId;
    //商品名称
    private String productName;
    //商品单价
    private BigDecimal productPrice;
    //商品库存
    private Integer productStock;
    //商品描述
    private String productDescription;
    //商品小图
    private String productIcon;
    //类目编号
    private Integer categoryType;
    //商品状态
    private Integer productStatus=ProductStatusEnum.UP.getCode();
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

@JsonIgnore
public ProductStatusEnum getProductStatusEnum(){
    return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
}
}
