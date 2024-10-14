package com.pillgood.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pillgood.dto.OrdersDto;
import com.pillgood.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController

public class OrderRestController {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping("/order")
    public ResponseEntity<OrdersDto> createOrder(@RequestBody OrdersDto orderDto ) {
        OrdersDto createdOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<OrdersDto>(createdOrder, HttpStatus.CREATED);
    }
    
    //카트에서 주문
    @PostMapping("/order/cart")
    public ResponseEntity<OrdersDto> createOrderFromCart(@RequestBody OrdersDto ordersDto) {
        OrdersDto createdOrder = orderService.createOrderFromCart(ordersDto);
        return new ResponseEntity<OrdersDto>(createdOrder, HttpStatus.CREATED);
    }

    // 주문 전체 조회
    @GetMapping("/order/user/{userId}")
    public ResponseEntity<List<OrdersDto>> getAllOrders(@PathVariable(name = "userId") Long userId) {
        List<OrdersDto> orders = orderService.orderDtoList(userId);
        return ResponseEntity.ok(orders);
    }

    // 주문 상세 조회
    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrdersDto> getOrderDetail(@PathVariable(name = "orderId") Long orderId) {
        OrdersDto order = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(order);
    }

    // 주문 삭제
    @DeleteMapping("/order/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }
}