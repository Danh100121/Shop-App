package com.example.shopapp.controllers;

import com.example.shopapp.dtos.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {
    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO){
        return ResponseEntity.ok("create order details here");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(@Valid @PathVariable("id") Long id){
        return ResponseEntity.ok("getOrderDetail with id" + id);
    }

    // lay ra danh sach cac order detail cua 1 order nao do
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(@Valid @PathVariable("orderId") Long orderId){
        return ResponseEntity.ok("getOrderDetail with orderId" + orderId);
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
