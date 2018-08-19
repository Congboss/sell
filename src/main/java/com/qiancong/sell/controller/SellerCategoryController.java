package com.qiancong.sell.controller;

import com.qiancong.sell.entity.ProductCategory;
import com.qiancong.sell.exception.SellException;
import com.qiancong.sell.form.CategoryForm;
import com.qiancong.sell.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @program: sell
 * @description:卖家类目
 * @author: Cong.Qian
 * @create: 2018-06-17 18:02
 **/
@Controller
@RequestMapping("l")
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> categoryList=categoryService.findAll();

        map.put("categoryList",categoryList);
        return new ModelAndView("category/list",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              Map<String,Object> map){
        if(categoryId!=null){
         ProductCategory productCategory=categoryService.findOne(categoryId);
         map.put("category",productCategory);
        }
        return new ModelAndView("category/index",map);
    }
        /**
        * @Description: 保存
        * @Param:
        * @return:
        * @Autho: Cong.Qian
        * @Date: 2018/6/17
        */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        ProductCategory productCategory=new ProductCategory();
        try{
            if(form.getCategoryId()!=null){
                productCategory=categoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form,productCategory);
            categoryService.save(productCategory);
        }catch (SellException e){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success",map);



    }
}
