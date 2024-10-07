package com.pillgood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgood.entity.Cart;
import com.pillgood.entity.Item;
import com.pillgood.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long>{

	Cart findByUserAndItem(User user, Item item);

	List<Cart> findByUser(User user);

	  List<Cart> findByCartNoIn(List<Long> cartIds);
}
