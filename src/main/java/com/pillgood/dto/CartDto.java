package com.pillgood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartDto {
	
	private Long cartId;
	private Integer cartQty;
	private Long itemId; // 장바구니에 담긴 상품 정보
	private Long userId; // 장바구니를 소유한 사용자 정보
	private ItemDto itemDto;
	


}
