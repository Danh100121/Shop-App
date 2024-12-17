package com.example.shopapp.controllers;

import com.example.shopapp.dtos.OrderDetailDTO;
import com.example.shopapp.models.OrderDetail;
import com.example.shopapp.services.OrderDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order_details")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO){
        try {
            OrderDetail newOrderDetail = orderDetailService.createOrderDetail(orderDetailDTO);
            return ResponseEntity.ok().body(newOrderDetail);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(@Valid @PathVariable("id") Long id) throws Exception {
            OrderDetail orderDetailExisting = orderDetailService.getOrderDetail(id);
            return ResponseEntity.ok(orderDetailExisting);
    }

    // lay ra danh sach cac order detail cua 1 order nao do
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(@Valid @PathVariable("orderId") Long orderId){
        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderId);
        return ResponseEntity.ok(orderDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(@Valid  @PathVariable("id") Long id,
                                               @RequestBody OrderDetailDTO orderDetailDTO){
        return ResponseEntity.ok("updateOrderDetail with id = " + id + "," + "OrderDetailData:" + orderDetailDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@Valid @PathVariable("id") Long id){
        return ResponseEntity.noContent().build();
    }
}
