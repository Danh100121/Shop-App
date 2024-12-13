package com.example.shopapp.services;

import com.example.shopapp.dtos.OrderDTO;
import com.example.shopapp.exceptions.DataNotFoundException;
import com.example.shopapp.models.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderDTO orderDTO) throws Exception;

    Order getOrder(Long orderId) throws Exception;

    Order updateOrder(Long orderId, OrderDTO orderDTO) throws Exception;

    void deleteOrder(Long orderId);

    List<Order> ordersFindByUserId(Long userId) throws Exception;
}
