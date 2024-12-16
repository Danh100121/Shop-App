package com.example.shopapp.services.serviceImpl;

import com.example.shopapp.dtos.OrderDetailDTO;
import com.example.shopapp.models.OrderDetail;
import com.example.shopapp.services.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Override
    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) {
        return null;
    }

    @Override
    public OrderDetail getOrderDetail(Long id) {
        return null;
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) {
        return null;
    }

    @Override
    public void deleteOrderDetail(Long id) {

    }

    @Override
    public List<OrderDetail> getOrderDetails(Long orderId) {
        return List.of();
    }
}
