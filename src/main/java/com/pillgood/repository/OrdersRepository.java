package com.pillgood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgood.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

	List<Orders> findByUserId(Long userId);

}
