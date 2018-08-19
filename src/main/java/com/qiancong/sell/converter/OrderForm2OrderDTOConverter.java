package com.qiancong.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qiancong.sell.entity.OrderDetail;
import com.qiancong.sell.dto.OrderDTO;
import com.qiancong.sell.enums.ResultEnum;
import com.qiancong.sell.exception.SellException;
import com.qiancong.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: sell
 * @description:
 * @author: Cong.Qian
 * @create: 2018-06-14 14:30
 **/
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm){

        Gson gson=new Gson();
        OrderDTO orderDTO=new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());

        orderDTO.setBuyerPhone(orderForm.getPhone());

        orderDTO.setBuyerAddress(orderForm.getAddress());

        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList=new ArrayList<>();
try {
    orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
    }.getType());
}
catch (Exception e){
    log.error("【对象转换】 错误，String={}",orderForm.getItems());
    throw new SellException(ResultEnum.PARAM_ERROR);
}
orderDTO.setOrderDetailList(orderDetailList);

return orderDTO;
    }
}
