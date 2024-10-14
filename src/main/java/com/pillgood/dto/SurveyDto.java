package com.pillgood.dto;

import com.pillgood.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SurveyDto {
	
	private Long id;
	private String height;
	private String weight;
	private String symptomType;
	private String key;
	private Long userId;
	

}
