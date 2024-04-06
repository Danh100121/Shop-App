package com.example.shopapp.controllers;

import com.example.shopapp.dtos.OrderDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    @PostMapping("")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO orderDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("create Order successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getOrders(@Valid @PathVariable("user_id") Long userId){
        try{
                return ResponseEntity.ok("Get list order from user_id");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@Valid @PathVariable("id") Long id,
                                         @Valid @RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok("update information order");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@Valid @PathVariable("id") Long id){
        // xoa mem => cap nhat field active = false
        return ResponseEntity.ok("Delete order successfully");
    }
}
