package com.example.shopapp.services.serviceImpl;

import com.example.shopapp.dtos.OrderDTO;
import com.example.shopapp.exceptions.DataNotFoundException;
import com.example.shopapp.models.Order;
import com.example.shopapp.models.OrderStatus;
import com.example.shopapp.models.User;
import com.example.shopapp.repositories.OrderRepository;
import com.example.shopapp.repositories.UserRepository;
import com.example.shopapp.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public Order createOrder(OrderDTO orderDTO) throws Exception {
        // Tìm xem userId có tồn tại hay không
        User user = userRepository
                .findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: "+ orderDTO.getUserId()));

        // Convert orderDTO => Order
        // Dùng thư viện modelmapper
        // Tạo một luồng bằng ánh xạ riêng ể kiểm soát việc ánh xạ
        modelMapper.typeMap(OrderDTO.class, Order.class)
                .addMappings(mapper -> mapper.skip(Order::setId));

        // Cập nhật các trường từ đơn hàng
        Order order = new Order();
        modelMapper.map(orderDTO, order);
        order.setUser(user);
        order.setOrderDate(new Date()); // Lấy thời điểm hiện tại
        order.setStatus(OrderStatus.PENDING);

        // Kiểm tra shipping date phải >= ngày hôm nay
        LocalDate shippingDate = orderDTO.getShippingDate() == null ? LocalDate.now() : orderDTO.getShippingDate();
        if (shippingDate.isBefore(LocalDate.now())) {
            throw new DataNotFoundException("Date must be at least date");
        }

        order.setActive(true);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order getOrder(Long orderId) throws Exception {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find order by id: "+ orderId));
    }

    @Override
    public Order updateOrder(Long orderId, OrderDTO orderDTO) throws Exception {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find order by id: "+ orderId));

        User userExisting = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: "+ orderDTO.getUserId()));

        modelMapper.typeMap(OrderDTO.class, Order.class)
                .addMappings(mapper -> mapper.skip(Order::setId));
        modelMapper.map(orderDTO, order);
        order.setUser(userExisting);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        // no hard-delete => please soft-delete
        if(order != null) {
          order.setActive(false);
          orderRepository.save(order);
        }
    }

    @Override
    public List<Order> ordersFindByUserId(Long userId) throws Exception {
        userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: "+userId));
        return orderRepository.findByUserId(userId);
    }
}
