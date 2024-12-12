package com.example.shopapp.services;

import com.example.shopapp.dtos.OrderDTO;
import com.example.shopapp.responses.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderDTO orderDTO) throws Exception;

    OrderResponse getOrder(Long orderId);

    OrderResponse updateOrder(Long orderId, OrderDTO orderDTO);

    void deleteOrder(Long orderId);

    List<OrderResponse> getAllOrders(Long userId);
}
