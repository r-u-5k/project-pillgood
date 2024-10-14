package com.pillgood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AdminDto {

	private Long adminNo;
	private String adminId;
	private String adminPassword;
	private String adminRole;

}
