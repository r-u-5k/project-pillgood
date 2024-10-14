package com.pillgood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pillgood.dto.CartDto;
import com.pillgood.service.CartService;

@RestController
public class CartRestController {

    @Autowired
    private CartService cartService;

    // 장바구니 담기 
    @PostMapping("/api/cart")
    public ResponseEntity<String> addToCart(@RequestBody CartDto cartDto) {
			cartService.addToCart(cartDto);
        return ResponseEntity.status(HttpStatus.OK).body("장바구니 담기 성공");
    }

    // 장바구니 아이템 삭제
    @DeleteMapping("/api/cart/{cartId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable(name = "cartId") Long cartId) {
        cartService.removeFromCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 장바구니 아이템 수량 플러스
    @PutMapping("/api/cart/plus/{cartId}")
    public ResponseEntity<Void> updateCartItemQuantityPlus(@PathVariable(name = "cartId") Long cartId) {
        cartService.plusCartItemQty(cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // 장바구니 아이템 수량 마이너스
    @PutMapping("/api/cart/minus/{cartId}")
    public ResponseEntity<Void> updateCartItemQuantityMinus(@PathVariable(name = "cartId") Long cartId) {
    	cartService.minusCartItemQty(cartId);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    

    // 장바구니 조회
    @GetMapping("/api/cart/{userId}")
    public ResponseEntity<List<CartDto>> getCartItems(@PathVariable(name = "userId") Long userId) {
        List<CartDto> cartItems = cartService.getCartItems(userId);
        return ResponseEntity.ok(cartItems);
    }
}