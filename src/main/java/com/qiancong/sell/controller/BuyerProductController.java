package com.qiancong.sell.controller;

import com.qiancong.sell.VO.ProductInfoVO;
import com.qiancong.sell.VO.ProductVO;
import com.qiancong.sell.VO.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
@GetMapping("/list")
    public ResultVO list(){
    //1.查询所有上架商品

    //2.查询类目（一次性查询）

    //3.数据拼装
    ResultVO resultVO=new ResultVO();
    ProductVO productVO=new ProductVO();
    ProductInfoVO productInfoVO=new ProductInfoVO();
    resultVO.setCode(1);
    resultVO.setMessage("成功");
    productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
    resultVO.setData(Arrays.asList(productVO));

return resultVO;
}
}
