package com.pillgood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItemDto {

	private Long orderItemNo;
	private Integer orderItemQty;
	private ItemDto item; // 상품 아이디
	private Long orderId; // 주문 아이디
	

}
