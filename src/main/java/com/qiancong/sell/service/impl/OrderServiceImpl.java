package com.qiancong.sell.service.impl;

import com.qiancong.sell.converter.OrderMaster2OrderDTOConverter;
import com.qiancong.sell.mapper.OrderDetailRepository;
import com.qiancong.sell.mapper.OrderMasterRepository;
import com.qiancong.sell.entity.OrderDetail;
import com.qiancong.sell.entity.OrderMaster;
import com.qiancong.sell.entity.ProductInfo;
import com.qiancong.sell.dto.CartDTO;
import com.qiancong.sell.dto.OrderDTO;
import com.qiancong.sell.enums.OrderStatusEnum;
import com.qiancong.sell.enums.PayStatusEnum;
import com.qiancong.sell.enums.ResultEnum;
import com.qiancong.sell.exception.SellException;
import com.qiancong.sell.service.OrderService;
import com.qiancong.sell.service.ProductService;
import com.qiancong.sell.service.WebSocket;
import com.qiancong.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sell
 * @description:
 * @author: Cong.Qian
 * @create: 2018-06-13 16:58
 **/
@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository repository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private WebSocket webSocket;
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId=KeyUtil.genUniqueKey();
        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);
        //1.查询商品（数量，价格）
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo=productService.findOne(orderDetail.getProductId());
            if(productInfo==null){
throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价
            orderAmount=productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            repository.save(orderDetail);
        }
//3.写入订单数据库（orderMaster和orderDetail）
        OrderMaster orderMaster=new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);


        //4.扣库存
        List<CartDTO> cartDTOList= orderDTO.getOrderDetailList().stream().map(e ->
        new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        //发送Websocket消息
        webSocket.sendMessage(orderDTO.getOrderId());
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster=orderMasterRepository.findById(orderId).get();
        if(orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList=repository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
      Page<OrderMaster>  orderMasterPage=orderMasterRepository
              .findByBuyerOpenid(buyerOpenid,pageable);

       List<OrderDTO> orderDTOList= OrderMaster2OrderDTOConverter
               .convert(orderMasterPage.getContent());

       return new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }
//取消订单
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster=new OrderMaster();

        //判断订单状态
if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
    log.info("【取消订单】 订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
    throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
}
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
OrderMaster updateResult=orderMasterRepository.save(orderMaster);
if(updateResult==null){
    log.info("【取消订单】 更新失败,orderMaster={}",orderMaster);
    throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
}
        //返回库存
if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
    log.info("【取消订单】 订单中无商品详情，orderDTO={} ",orderDTO);
    throw new SellException(ResultEnum.ORDER_DETAIL_ERROR);
}
List<CartDTO> cartDTOList=orderDTO.getOrderDetailList().stream()
        .map(e ->new CartDTO(e.getProductId(),e.getProductQuantity()))
        .collect(Collectors.toList());
productService.increaseStock(cartDTOList);
        //如果已支付，退款
if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
    //TODO
}
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.info("【完结订单】 订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if(updateResult==null){
            log.error("【完结订单】 更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
    log.info("【完结订单】 订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
    throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
}

        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
OrderMaster orderMaster=new OrderMaster();
BeanUtils.copyProperties(orderDTO,orderMaster);
OrderMaster result=orderMasterRepository.save(orderMaster);
if(result==null){
    log.error("【订单支付完成】 更新失败，orderMaster={}",orderMaster);
    throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
}
        return orderDTO;
    }

//查询所有订单
    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
      Page<OrderMaster> orderMasterPage=  orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList= OrderMaster2OrderDTOConverter
                .convert(orderMasterPage.getContent());

        return new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }


}
