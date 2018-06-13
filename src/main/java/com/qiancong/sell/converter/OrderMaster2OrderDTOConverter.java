package com.qiancong.sell.converter;

import com.qiancong.sell.dataobject.OrderMaster;
import com.qiancong.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sell
 * @description: 转换器
 * @author: Cong.Qian
 * @create: 2018-06-13 21:53
 **/
public class OrderMaster2OrderDTOConverter {
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO=new OrderDTO();

        BeanUtils.copyProperties(orderMaster,orderDTO);

        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
       return orderMasterList.stream().map(e ->
        convert(e)
        ).collect(Collectors.toList());
    }
}
