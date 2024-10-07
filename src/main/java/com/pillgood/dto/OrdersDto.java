package com.pillgood.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrdersDto {
	
    private Long orderId; 
    private String ordersName;
    private String ordersPhone;
    private String ordersZipcode;
    private String ordersAddress;
    private String ordersAddressDetail;
    private Long ordersPrice;
    private Long userId; 
    private Date orderDate;
    private Long paymentId; //나중에 결제 추가시 사용 
    
    @Default
    private List<Long> cartItems= new ArrayList<>();
    
    @Default
    private List<OrderItemDto> orderItems= new ArrayList<>(); // 주문된 상품 목록
    	
    	
    

}
