package com.qiancong.sell.controller;

import com.qiancong.sell.dataobject.ProductCategory;
import com.qiancong.sell.dataobject.ProductInfo;
import com.qiancong.sell.exception.SellException;
import com.qiancong.sell.form.ProductForm;
import com.qiancong.sell.service.CategoryService;
import com.qiancong.sell.service.ProductService;
import com.qiancong.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @program: sell
 * @description:卖家端商品
 * @author: Cong.Qian
 * @create: 2018-06-17 13:26
 **/
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    CategoryService categoryService;
    //列表
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request=new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage=productService.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }
        /**
        * @Description: 商品上架
        * @Param:
        * @return:
        * @Author: Cong.Qian
        * @Date: 2018/6/17
        */
@GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,Map<String,Object> map){
map.put("productId",productId);
try {
    productService.onSale(productId);
}
catch (SellException e){
    map.put("msg",e.getMessage());
map.put("url","/sell/seller/product/list");
return new ModelAndView("common/error",map);
}
    map.put("url","/sell/seller/product/list");
return new ModelAndView("common/success",map);
    }

    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,Map<String,Object> map){
        map.put("productId",productId);
        try {
            productService.offSale(productId);
        }
        catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/index")
    public ModelAndView  index(@RequestParam(value = "productId",required = false) String productId,Map<String,Object> map){
    if(!StringUtils.isEmpty((productId))){
        ProductInfo productInfo=productService.findOne(productId);
        map.put("productInfo",productInfo);
    }
        //查询所有类目
        List<ProductCategory> categoryList=categoryService.findAll();
map.put("categoryList",categoryList);

return new ModelAndView("product/index",map);
    }
    /**
    * @Description:  保存/更新
    * @Param:
    * @return:
    * @Author: Cong.Qian
    * @Date: 2018/6/17
    */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        ProductInfo productInfo=new ProductInfo();
        try {
            //如果productId为空，说明是新增
            if(!StringUtils.isEmpty(productForm.getProductId())){
                productInfo= productService.findOne(productForm.getProductId());
            }else{
                productForm.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(productForm,productInfo);

            productService.save(productInfo);
        }
        catch (SellException e)
        {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

}

