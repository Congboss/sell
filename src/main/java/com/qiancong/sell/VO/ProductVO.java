package com.qiancong.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qiancong.sell.dataobject.ProductInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
