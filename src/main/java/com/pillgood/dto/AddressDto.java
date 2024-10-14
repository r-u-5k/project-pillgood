package com.pillgood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {

	private Long addressNo;
	private String name;
	private String phone;
	private String zipcode;
	private String address;
	private String addressDetail;
	private String request;
	private Boolean defaultAddress;
	private Long userId;
}
