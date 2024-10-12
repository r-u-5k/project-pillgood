package com.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgood.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
