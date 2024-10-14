package com.pillgood.mapper;


import com.pillgood.dto.AddressDto;
import com.pillgood.dto.OrdersDto;
import com.pillgood.entity.Address;
import com.pillgood.entity.Orders;


public class ToEntityMapper {

	
	public static Orders toOrdersEntity(OrdersDto orderDto) {
		return Orders.builder().
				ordersAddress(orderDto.getOrdersAddress())
				.ordersAddressDetail(orderDto.getOrdersAddressDetail())
				.ordersPhone(orderDto.getOrdersPhone())
				.ordersPrice(orderDto.getOrdersPrice())
				.ordersZipcode(orderDto.getOrdersZipcode())
				.build();
	}
	public static Address toAddressEntity (AddressDto addressDto) {
		
		return Address.builder()
				.address(addressDto.getAddress())
				.addressDetail(addressDto.getAddressDetail())
				.name(addressDto.getName())
				.phone(addressDto.getPhone())
				.zipcode(addressDto.getZipcode())
				.build();
	}
	
	
}
