package com.example.shopapp.services.serviceImpl;

import com.example.shopapp.dtos.OrderDetailDTO;
import com.example.shopapp.exceptions.DataNotFoundException;
import com.example.shopapp.models.Order;
import com.example.shopapp.models.OrderDetail;
import com.example.shopapp.models.Product;
import com.example.shopapp.repositories.OrderDetailRepository;
import com.example.shopapp.repositories.OrderRepository;
import com.example.shopapp.repositories.ProductRepository;
import com.example.shopapp.services.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    @Override
    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        Order order = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find order with id:" +orderDetailDTO.getOrderId()));

        Product product = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find product with id:" +orderDetailDTO.getProductId()));

        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .numberOfProducts(orderDetailDTO.getNumberOfProduct())
                .totalMoney(orderDetailDTO.getTotalMoney())
                .color(orderDetailDTO.getColor())
                .build();

        // Lưu vào db
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetail(Long id) throws Exception {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find order detail with id:" +id));
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) {
        return null;
    }

    @Override
    public void deleteOrderDetail(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}
