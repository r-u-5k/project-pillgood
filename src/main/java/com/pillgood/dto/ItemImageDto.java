package com.pillgood.dto;

import com.pillgood.entity.ItemImage.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItemImageDto {
	
	private String img;
	private String url;
	private Type type;
}
