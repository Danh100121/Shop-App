package com.example.shopapp.services;

import com.example.shopapp.dtos.OrderDetailDTO;
import com.example.shopapp.models.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception;

    OrderDetail getOrderDetail(Long id) throws Exception;

    OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO);

    void deleteOrderDetail(Long id);

    List<OrderDetail> findByOrderId(Long orderId);
}
