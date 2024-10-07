package com.pillgood.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pillgood.dto.CartDto;
import com.pillgood.entity.Cart;
import com.pillgood.entity.Item;
import com.pillgood.entity.User;
import com.pillgood.exception.CartNotFoundException;
import com.pillgood.exception.ItemNotFoundException;
import com.pillgood.exception.UserNotFoundException;
import com.pillgood.mapper.ToDtoMapper;
import com.pillgood.repository.CartRepository;
import com.pillgood.repository.ItemRepository;
import com.pillgood.repository.UserRepository;

import lombok.Data;

@Data
@Transactional
@Service
public class CartService {

	private final CartRepository cartRepository;
	private final ItemRepository itemRepository;
	private final UserRepository userRepository;

	public void addToCart(CartDto cartDto) {
		// 상품 조회
		Item item = itemRepository.findById(cartDto.getItemId()).orElseThrow(() -> new ItemNotFoundException("아이템없음"));
		User user = userRepository.findById(cartDto.getUserId()).orElseThrow(() -> new UserNotFoundException("유저가없어요"));
		// 장바구니에 추가할 상품이 이미 있는지 확인
		Cart existingCartItem = cartRepository.findByUserAndItem(user, item);
		if (existingCartItem != null) {
			// 이미 장바구니에 있는 상품이면 수량만 업데이트
			existingCartItem.setCartQty(existingCartItem.getCartQty() + cartDto.getCartQty());
		} else {
			// 장바구니에 없는 상품이면 새로 추가
			Cart cart = new Cart();
			cart = CartService.toEntity(cartDto, item, user);
			cart.setCartQty(cartDto.getCartQty());
			cart.setUser(user);
			cart.setItem(item);
			cartRepository.save(cart);
		}
	}

	public void removeFromCart(Long cartId) {
		// 장바구니에서 상품 제거
		cartRepository.deleteById(cartId);
	}

	public void updateCartItemQuantity(CartDto cartDto) {
		// 장바구니 상품 수량 변경
		Cart cart = cartRepository.findById(cartDto.getCartId()).orElseThrow(() -> new CartNotFoundException("카트못찾음"));
		cart.setCartQty(cartDto.getCartQty());
		cartRepository.save(cart);
	}
	
	public void plusCartItemQty(Long cartId) {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("카트못찾음"));
		cart.setCartQty(cart.getCartQty()+1);
		cartRepository.save(cart);
	}
	public void minusCartItemQty(Long cartId) {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("카트못찾음"));
		cart.setCartQty(cart.getCartQty()-1);
		cartRepository.save(cart);
	}

	// 장바구니 조회

	public List<CartDto> getCartItems(Long loginUserId) {
		User user = userRepository.findById(loginUserId).orElseThrow(() -> new UserNotFoundException("유저못찾음"));
		List<Cart> carts = user.getCarts();
		System.out.println(carts);
		List<CartDto> cartDtos = carts.stream().map(ToDtoMapper::toCartDto).collect(Collectors.toList());
		return cartDtos;
	}

	

	public static Cart toEntity(CartDto cartDto, Item item, User user) {
		Cart cart = new Cart();
		cart.setCartNo(cartDto.getCartId());
		cart.setCartQty(cartDto.getCartQty());
		cart.setItem(item);
		cart.setUser(user);
		return cart;
	}

}