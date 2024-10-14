package com.pillgood.dto;




import java.util.List;

import com.pillgood.entity.Category;
import com.pillgood.entity.ItemImage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemDto {
	 	private Long no;
	    private String brand;
	    private String name;
	    private Long price;
	    private String description;
	    private Category.Symptom categorySymptom;
	    private Category.Type categoryType;
	    private List<ItemImageDto> itemImageDto;
	    private Long reviewQty;
	    private Double reviewAvg;
	    
	   
}
